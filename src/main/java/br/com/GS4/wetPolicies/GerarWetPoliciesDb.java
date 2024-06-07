package br.com.GS4.wetPolicies;

import br.com.GS4.wetPolicies.core.model.entity.Proposicao;
import br.com.GS4.wetPolicies.core.service.ProposicaoService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

//@Component
public class GerarWetPoliciesDb {

    private final ProposicaoService proposicaoService;
    private final RestTemplate restTemplate;

//    @Autowired
    public GerarWetPoliciesDb(ProposicaoService proposicaoService, RestTemplate restTemplate) {
        this.proposicaoService = proposicaoService;
        this.restTemplate = restTemplate;
    }

//    @EventListener(ContextRefreshedEvent.class)
    public void init() {
        String[] keywords = {
                "poluição do oceano", "resíduos de plástico no oceano",
                "recursos hídricos", "fiscalização de portos",
                "fauna aquática", "resíduos na água", "resíduos no oceano",
                "biodiversidade marinha", "ecossistema aquático", "navio-pesqueiro",
                "fauna marinha", "flora marinha", "área marinha protegida",
                "aquecimento global", "acidificação dos oceanos", "zona costeira",
                "crimes ambientais", "recurso pesqueiro", "convenção sobre o direito do mar",
                "reservas marinhas", "áreas protegidas", "unidades de conservação"
        };

        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0");
        headers.set("Accept", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String baseUrl = "https://dadosabertos.camara.leg.br/api/v2/proposicoes";
        StringBuilder paramsBuilder = new StringBuilder();

        for (String keyword : keywords) {
            paramsBuilder.append("&keywords=").append(URLEncoder.encode(keyword, StandardCharsets.UTF_8));
        }
        paramsBuilder.append("&dataInicio=").append(URLEncoder.encode("2014-01-01", StandardCharsets.UTF_8));
        paramsBuilder.append("&dataFim=").append(URLEncoder.encode("2024-05-31", StandardCharsets.UTF_8));
        paramsBuilder.append("&pagina=").append(URLEncoder.encode("1", StandardCharsets.UTF_8));
        paramsBuilder.append("&itens=100");
        paramsBuilder.append("&ordem=ASC");
        paramsBuilder.append("&ordenarPor=id");

        String nextPageUrl = baseUrl + "?" + paramsBuilder.substring(1); // Removendo o primeiro "&" da string
        System.out.println("\n\n*** Url:" + nextPageUrl);

        boolean hasNextPage = true;

        while (hasNextPage) {
            System.out.println("\n*** Has Next Page");
            try {
                ResponseEntity<String> response = restTemplate.exchange(nextPageUrl, HttpMethod.GET, entity, String.class);
                String responseBody = convertXmlToJson(response.getBody());

                // Processar a resposta e salvar na tabela proposicoes
                JsonNode root = mapper.readTree(responseBody);
                System.out.println("\n\n*** root: " + root);
                JsonNode dados = root.path("dados");
                System.out.println("\n\n*** root path: " + dados);

                if (dados.isArray()) {
                    for (JsonNode item : dados) {
                        Proposicao proposicao = mapper.treeToValue(item, Proposicao.class);
                        proposicaoService.save(proposicao);
                        Thread.sleep(2000);
                    }
                }

                // Verificar se há mais páginas
                JsonNode links = root.path("links");
                hasNextPage = false;

                if (links.isArray()) {
                    for (JsonNode link : links) {
                        if (link.path("rel").asText().equals("next")) {
                            nextPageUrl = link.path("href").asText();
                            System.out.println("\n\n*** Next Page url:" + nextPageUrl);
                            hasNextPage = true;
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private String convertXmlToJson(String xml) {
        try {
            // Remover caracteres inválidos
            // xml = xml.replaceAll("[^\\x20-\\x7e]", "");

            org.json.JSONObject jsonObj = org.json.XML.toJSONObject(xml);
            return jsonObj.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "{}"; // Retorna um JSON vazio em caso de erro
        }
    }
}
