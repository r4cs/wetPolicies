package br.com.GS4.wetPolicies;

import br.com.GS4.wetPolicies.core.model.entity.Bancada;
import br.com.GS4.wetPolicies.core.model.entity.Deputado;
import br.com.GS4.wetPolicies.core.model.entity.Proposicao;
import br.com.GS4.wetPolicies.core.model.entity.VotacaoPorProposicao;
import br.com.GS4.wetPolicies.core.service.ProposicaoService;
import br.com.GS4.wetPolicies.core.service.VotacaoPorProposicaoService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Component
public class GerarVotacoesDb {

//    private final ProposicaoService proposicaoService;
//    private final VotacaoPorProposicaoService votacaoPorProposicaoService;
//    private final RestTemplate restTemplate;
//
//    @Autowired
//    public GerarVotacoesDb(ProposicaoService proposicaoService, VotacaoPorProposicaoService votacaoPorProposicaoService, RestTemplate restTemplate) {
//        this.proposicaoService = proposicaoService;
//        this.votacaoPorProposicaoService = votacaoPorProposicaoService;
//        this.restTemplate = restTemplate;
//    }
//
//    @EventListener(ContextRefreshedEvent.class)
//    public void init() {
//        List<Proposicao> proposicoes = proposicaoService.findAll();
//
//        ObjectMapper mapper = new ObjectMapper();
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("User-Agent", "Mozilla/5.0");
//        headers.set("Accept", "application/json");
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        for (Proposicao proposicao : proposicoes) {
//            String tipo = proposicao.getSiglaTipo();
//            String numero = String.valueOf(proposicao.getNumero());
//            String ano = String.valueOf(proposicao.getAno());
//
//            String url = "https://www.camara.leg.br/SitCamaraWS/Proposicoes.asmx/ObterVotacaoProposicao"
//                    + "?tipo=" + URLEncoder.encode(tipo, StandardCharsets.UTF_8)
//                    + "&numero=" + URLEncoder.encode(numero, StandardCharsets.UTF_8)
//                    + "&ano=" + URLEncoder.encode(ano, StandardCharsets.UTF_8);
//
//            try {
//                ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
//                String responseBody = response.getBody();
//
//                System.out.println("*** Response body " + responseBody);
//
//                // Processar a resposta e salvar na tabela votacoes
//                JsonNode root = mapper.readTree(responseBody);
//                JsonNode votacoesNode = root.path("proposicao").path("Votacoes").path("Votacao");
//                System.out.println("*** Votacoes: " + responseBody);
//
//                if (votacoesNode.isObject()) {
//                    VotacaoPorProposicao votacao = new VotacaoPorProposicao();
//                    votacao.setProposicao(proposicao);
//                    votacao.setResumo(votacoesNode.path("Resumo").asText());
//                    votacao.setData(mapper.convertValue(votacoesNode.path("Data"), Date.class));
//                    votacao.setHora(votacoesNode.path("Hora").asText());
//                    votacao.setObjVotacao(votacoesNode.path("ObjVotacao").asText());
//                    votacao.setCodSessao(votacoesNode.path("codSessao").asText());
//
//                    List<Bancada> bancadas = new ArrayList<>();
//                    JsonNode bancadasNode = votacoesNode.path("orientacaoBancada").path("bancada");
//                    if (bancadasNode.isArray()) {
//                        for (JsonNode bancadaNode : bancadasNode) {
//                            Bancada bancada = new Bancada();
//                            bancada.setSigla(bancadaNode.path("Sigla").asText());
//                            bancada.setOrientacao(bancadaNode.path("orientacao").asText());
//                            bancada.setVotacao(votacao);
//                            bancadas.add(bancada);
//                        }
//                    }
//                    votacao.setBancadas(bancadas);
//
//                    List<Deputado> deputados = new ArrayList<>();
//                    JsonNode deputadosNode = votacoesNode.path("votos").path("Deputado");
//                    if (deputadosNode.isArray()) {
//                        for (JsonNode deputadoNode : deputadosNode) {
//                            Deputado deputado = new Deputado();
//                            deputado.setNome(deputadoNode.path("Nome").asText());
//                            deputado.setIdeCadastro(deputadoNode.path("ideCadastro").asText());
//                            deputado.setPartido(deputadoNode.path("Partido").asText());
//                            deputado.setUf(deputadoNode.path("UF").asText());
//                            deputado.setVoto(deputadoNode.path("Voto").asText());
//                            deputado.setVotacao(votacao);
//                            deputados.add(deputado);
//                        }
//                    }
//                    votacao.setDeputados(deputados);
//
//                    votacaoPorProposicaoService.save(votacao);
//                    Thread.sleep(1000); // Pausa para respeitar o rate limit
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                System.out.println("Error fetching votacao for proposicao " + proposicao.getId() + ": " + e.getMessage());
//            }
//        }
//    }
}

