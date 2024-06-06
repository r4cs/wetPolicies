package br.com.GS4.wetPolicies;

import br.com.GS4.wetPolicies.core.model.entity.Bancada;
import br.com.GS4.wetPolicies.core.model.entity.Deputado;
import br.com.GS4.wetPolicies.core.model.entity.Proposicao;
import br.com.GS4.wetPolicies.core.model.entity.Votacao;
import br.com.GS4.wetPolicies.core.service.DeputadoService;
import br.com.GS4.wetPolicies.core.service.ProposicaoService;
import br.com.GS4.wetPolicies.core.service.VotacaoService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Component
public class GerarVotacoesDb {

    private final ProposicaoService proposicaoService;
    private final DeputadoService deputadoService;
    private final VotacaoService votacaoService;
    private final RestTemplate restTemplate;

//    @Autowired
    public GerarVotacoesDb(ProposicaoService proposicaoService, DeputadoService deputadoService, VotacaoService votacaoService, RestTemplate restTemplate) {
        this.proposicaoService = proposicaoService;
        this.deputadoService = deputadoService;
        this.votacaoService = votacaoService;
        this.restTemplate = restTemplate;
    }

//    @EventListener(ContextRefreshedEvent.class)
    public void init() {
        List<Proposicao> proposicoes = proposicaoService.findAll();

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

            System.out.println("\n\n*** Url da proposicao: " + url);


            try {
                ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
                String responseBody = convertXmlToJson(response.getBody());

                JsonNode root = mapper.readTree(responseBody);
                JsonNode votacoesNode = root.path("proposicao").path("Votacoes").path("Votacao");
                System.out.println("*** Votacoes: " + responseBody);

                if (votacoesNode.isObject()) {
                    Votacao votacao = new Votacao();
//                    votacao.setId(proposicao.getId());
                    votacao.setProposicao(proposicao);

                    String resumo = votacoesNode.path("Resumo").asText();
                    if (resumo.length() > 8000) {
                        resumo = resumo.substring(0, 8000);
                    }
//                    votacao.setResumo(resumo);

                    votacao.setData(votacoesNode.path("Data").asText());
//                    votacao.setHora(votacoesNode.path("Hora").asText());
//                    votacao.setObjVotacao(votacoesNode.path("ObjVotacao").asText());
//                    votacao.setCodSessao(votacoesNode.path("codSessao").asText());

                    List<Bancada> bancadas = new ArrayList<>();
                    JsonNode bancadasNode = votacoesNode.path("orientacaoBancada").path("bancada");
                    if (bancadasNode.isArray()) {
                        for (JsonNode bancadaNode : bancadasNode) {
                            Bancada bancada = new Bancada();
//                            bancada.setSigla(bancadaNode.path("Sigla").asText());
//                            bancada.setOrientacao(bancadaNode.path("orientacao").asText());
//                            bancada.setVotacao(votacao);
                            bancadas.add(bancada);
                        }
                    }
//                    votacao.setBancadas(bancadas);

//                    votacaoPorProposicaoService.save(votacao); // Persistindo a votação antes dos deputados

                    List<Deputado> deputados = new ArrayList<>();
                    JsonNode deputadosNode = votacoesNode.path("votos").path("Deputado");
                    if (deputadosNode.isArray()) {
                        for (JsonNode deputadoNode : deputadosNode) {
                            Optional<Deputado> deputado = deputadoService.findById(deputadoNode.path("ideCadastro").asInt());
                            if (!deputado.isPresent()) {
                                Deputado d = new Deputado();
                                d.setIdeCadastro(deputadoNode.path("ideCadastro").asInt());
                                d.setNome(deputadoNode.path("Nome").asText());
                                d.setPartido(deputadoNode.path("Partido").asText());
                                d.setUf(deputadoNode.path("UF").asText());
                                d.setVotacao(votacao);
                                deputados.add(d);
//                                deputadoService.save(d);
                            } else {
                                deputados.add(deputado.get());
                            }
                        }
                    }
//                    votacao.setDeputados(deputados);

                    votacaoService.save(votacao); // Persistindo a votação novamente com os deputados associados

                    Thread.sleep(2000); // Pausa para respeitar o rate limit
                }
            } catch (Exception e) {
                e.printStackTrace();
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

