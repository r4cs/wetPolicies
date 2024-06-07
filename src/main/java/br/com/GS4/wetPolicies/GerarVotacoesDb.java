package br.com.GS4.wetPolicies;

import br.com.GS4.wetPolicies.core.model.entity.Bancada;
import br.com.GS4.wetPolicies.core.model.entity.OrientacaoBancada;
import br.com.GS4.wetPolicies.core.model.entity.Proposicao;
import br.com.GS4.wetPolicies.core.model.entity.Votacao;
import br.com.GS4.wetPolicies.core.service.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

//@Component
public class GerarVotacoesDb {

    private final ProposicaoService proposicaoService;
    private final OrientacaoBancadaService orientacaoBancadaService;
    private final BancadaService bancadaService;
    private final VotacaoService votacaoService;
    private final RestTemplate restTemplate;

//    @Autowired
    public GerarVotacoesDb(ProposicaoService proposicaoService,
                           OrientacaoBancadaService orientacaoBancadaService,
                           BancadaService bancadaService,
                           VotacaoService votacaoService,
                           RestTemplate restTemplate) {
        this.proposicaoService = proposicaoService;
        this.orientacaoBancadaService = orientacaoBancadaService;
        this.bancadaService = bancadaService;
        this.votacaoService = votacaoService;
        this.restTemplate = restTemplate;
    }

//    @EventListener(ContextRefreshedEvent.class)
    public void init() {
        List<Proposicao> proposicoes = proposicaoService.findAll();
        System.out.println("\n*** Proposicoes size: " + proposicoes.size());

        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0");
        headers.set("Accept", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        for (Proposicao proposicao : proposicoes) {
            String tipo = proposicao.getSiglaTipo();
            String numero = String.valueOf(proposicao.getNumero());
            String ano = String.valueOf(proposicao.getAno());

            String url = "https://www.camara.leg.br/SitCamaraWS/Proposicoes.asmx/ObterVotacaoProposicao"
                    + "?tipo=" + URLEncoder.encode(tipo, StandardCharsets.UTF_8)
                    + "&numero=" + URLEncoder.encode(numero, StandardCharsets.UTF_8)
                    + "&ano=" + URLEncoder.encode(ano, StandardCharsets.UTF_8);

            System.out.println("\n*** Proposicao n: " + numero);
            System.out.println("*** Url da proposicao: " + url);


            try {
                ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
                String responseBody = convertXmlToJson(response.getBody());

                JsonNode root = mapper.readTree(responseBody);
                JsonNode votacoesNode = root.path("proposicao").path("Votacoes").path("Votacao");

                // votacoesNode {hora: string, resumo: string, orientacaoBancada: { orientacao, sigla } }
                if (votacoesNode.isObject()) {
                    Votacao votacao = new Votacao();
                    votacao.setProposicao(proposicao);
                    votacao.setData(votacoesNode.path("Data").asText());
                    votacaoService.save(votacao);

                    // bancadasNode {"bancada":[ {orientacao:string, "Sigla":"Bl UniPpFdrPsdbCid..."} ] }
                    JsonNode bancadasNode = votacoesNode.path("orientacaoBancada").path("bancada");
                    System.out.println("\n***Bancadas: " + bancadasNode);

                    if (bancadasNode.isArray()) {
                        for (JsonNode bancadaNode : bancadasNode) {
                            System.out.println("\n***Bancada node: " + bancadaNode);

                            Optional<Bancada> optionalBancada = bancadaService.findByNome(bancadaNode.path("Sigla").asText());
                            Bancada bancada;
                            if ( optionalBancada.isPresent() ) {
                                // se a bancada já existir, vamos incluir uma orientacao daquela bancada para aquela proposicao
                                bancada = optionalBancada.get();

                                OrientacaoBancada orientacaoBancada = new OrientacaoBancada();
                                orientacaoBancada.setVotacao(votacao);
                                orientacaoBancada.setOrientacao(bancadaNode.path("orientacao").asText());
                                orientacaoBancadaService.save(orientacaoBancada);

                            } else {
                                // se nao houver bancada, iremos cadastrar a bancada
                                Bancada novaBancada = new Bancada();
                                novaBancada.setNome(bancadaNode.path("Sigla").asText());
                                bancadaService.save(novaBancada);

                                // incluir a orientacao daquela bancada para aquela proposicao
                                OrientacaoBancada orientacaoBancada = new OrientacaoBancada();
                                orientacaoBancada.setVotacao(votacao);
                                orientacaoBancada.setBancada(novaBancada);
                                orientacaoBancada.setOrientacao(bancadaNode.path("orientacao").asText());
                                orientacaoBancadaService.save(orientacaoBancada);
                            }

                        }
                    }

//                    votacaoService.save(votacao); // Persistindo a votação novamente com os deputados associados

                    Thread.sleep(1000); // Pausa para respeitar o rate limit
                }
            } catch (Exception e) {
//                e.printStackTrace();
                System.out.println("*** Error fetching votacao for proposicao " + proposicao.getId() + ": " + e.getMessage());
            }
        }
    }

    private String convertXmlToJson(String xml) {
        try {
            org.json.JSONObject jsonObj = org.json.XML.toJSONObject(xml);
            return jsonObj.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "{}"; // Retorna um JSON vazio em caso de erro
        }
    }
}

