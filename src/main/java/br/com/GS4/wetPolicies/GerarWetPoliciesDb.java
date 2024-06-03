package br.com.GS4.wetPolicies;

import br.com.GS4.wetPolicies.core.model.entity.Proposicao;
import br.com.GS4.wetPolicies.core.service.ProposicaoService;
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

//@Component
public class GerarWetPoliciesDb {

//    private final ProposicaoService proposicaoService;
//    private final RestTemplate restTemplate;
//
//    @Autowired
//    public GerarWetPoliciesDb(ProposicaoService proposicaoService, RestTemplate restTemplate) {
//        this.proposicaoService = proposicaoService;
//        this.restTemplate = restTemplate;
//    }
//
//    @EventListener(ContextRefreshedEvent.class)
//    public void init() {
//        String[] keywords = {
//                "marinho", "rios", "peixe", "microplástico", "oceano",
//                "lago", "água", "tubarão", "baleia", "golfinho",
//                "tartaruga", "coral", "recife", "poluição", "plástico",
//                "pesca", "aquicultura", "navio", "navegação", "porto",
//                "costa", "maré", "corrente", "onda", "resíduo",
//                "água doce", "água salgada", "zona costeira", "biodiversidade marinha",
//                "ecossistema aquático", "pescador", "fauna marinha", "flora marinha",
//                "manguezal", "área marinha protegida", "aquecimento global", "acidificação dos oceanos"
//        };
//
//        String baseUrl = "https://dadosabertos.camara.leg.br/api/v2/proposicoes";
//        StringBuilder paramsBuilder = new StringBuilder();
//
//        try {
//            for (String keyword : keywords) {
//                paramsBuilder.append("&keywords=").append(URLEncoder.encode(keyword, StandardCharsets.UTF_8));
//            }
//            paramsBuilder.append("&dataInicio=").append(URLEncoder.encode("2017-01-01", StandardCharsets.UTF_8));
//            paramsBuilder.append("&dataFim=").append(URLEncoder.encode("2024-05-31", StandardCharsets.UTF_8));
//            paramsBuilder.append("&pagina=").append(URLEncoder.encode("5", StandardCharsets.UTF_8));
//            paramsBuilder.append("&itens=100");
//            paramsBuilder.append("&ordem=ASC");
//            paramsBuilder.append("&ordenarPor=id");
////
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("\n\n*** Base Url:" + baseUrl);
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("User-Agent", "Mozilla/5.0");
//        headers.set("Accept", "application/json");
//        System.out.println("\n*** Headers: :" + headers);
//
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        try {
//            boolean hasNextPage = true;
//            String nextPageUrl = baseUrl + "?" + paramsBuilder.substring(1); // Removendo o primeiro "&" da string
//            ObjectMapper mapper = new ObjectMapper();
//            System.out.println("\n*** Page 1 url:" + nextPageUrl);
//
//            while (hasNextPage) {
//                System.out.println("\n*** Has Next Page");
//                ResponseEntity<String> response = restTemplate.exchange(nextPageUrl, HttpMethod.GET, entity, String.class);
//                String responseBody = response.getBody();
//
//                // Processar a resposta e salvar na tabela proposicoes
//                JsonNode root = mapper.readTree(responseBody);
//                System.out.println("\n\n*** root: " + root);
//                JsonNode dados = root.path("dados");
//                System.out.println("\n\n*** root path: " + dados);
//
//                if (dados.isArray()) {
//                    for (JsonNode item : dados) {
//                        Proposicao proposicao = mapper.treeToValue(item, Proposicao.class);
//                        proposicaoService.save(proposicao);
//                        Thread.sleep(1000);
//                    }
//                }
//
//                // Verificar se há mais páginas
//                JsonNode links = root.path("links");
//                hasNextPage = false;
//
//                if (links.isArray()) {
//                    for (JsonNode link : links) {
//                        if (link.path("rel").asText().equals("next")) {
//                            nextPageUrl = link.path("href").asText();
//                            System.out.println("\n\n*** Next Page url:" + nextPageUrl);
//                            hasNextPage = true;
//                            break;
//                        }
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Error: " + e.getMessage());
//        }
//    }
}