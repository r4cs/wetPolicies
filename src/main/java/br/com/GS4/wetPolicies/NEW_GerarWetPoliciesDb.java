package br.com.GS4.wetPolicies;

import br.com.GS4.wetPolicies.core.model.entity.Proposicao;
import br.com.GS4.wetPolicies.core.service.dadosAbertosAPI.CamaraProposicaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//@Component
public class NEW_GerarWetPoliciesDb {

    private final CamaraProposicaoService camProposicaoService;

//    @Autowired
    public NEW_GerarWetPoliciesDb(CamaraProposicaoService camProposicaoService) {
        this.camProposicaoService = camProposicaoService;
    }

//    @EventListener(ContextRefreshedEvent.class)
    public void init() {
        String[] keywords = {
                "hídrico", "marinho", "oceano", "rios", "ribeirinha",
                "MarPOL", "UNCLOS", "MARPOL73/78", "ameaçadas",
                "marítimo", "OSPAR", "Cartagena", "pesca", "ecosistema"
        };

        ObjectMapper mapper = new ObjectMapper();
        

        Map<String, String> newParams = new LinkedHashMap<>();
        for (String keyword : keywords) {
            newParams.put("&keywords=", keyword);
        }
        newParams.put("&dataFim=", "2014-01-01");
        newParams.put("&dataInicio=", "2014-05-31");
        newParams.put("&pagina=", "1");
        newParams.put("&itens=", "15");
        newParams.put("&ordenarPor=", "id");

        boolean hasNextPage = true;

//        try {
//            List<Proposicao> proposicoes = camProposicaoService.getProposicoes(newParams);
//            System.out.println(proposicoes);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Error: " + e.getMessage());
//        }
    }

}
