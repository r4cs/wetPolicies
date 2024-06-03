package br.com.GS4.wetPolicies.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/sendRequest")
    public String sendRequest() {
        String baseUrl = "https://dadosabertos.camara.leg.br/api/v2/proposicoes";
        String params = "?keywords=marinho&keywords=rio&keywords=peixe&keywords=micropl%C3%A1stico&keywords=oceano&keywords=%C3%A1gua&keywords=tubar%C3%A3o&keywords=baleia&keywords=golfinho&dataInicio=2017-01-01&dataFim=2024-05-31&itens=4&ordem=ASC&ordenarPor=id";

        String fullUrl = baseUrl + params;
        System.out.println("\n\n*** full url: " + fullUrl);
        System.out.println("\n\n");
        try {
            String response = restTemplate.getForObject(fullUrl, String.class);
            return "Response from API: " + response;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
