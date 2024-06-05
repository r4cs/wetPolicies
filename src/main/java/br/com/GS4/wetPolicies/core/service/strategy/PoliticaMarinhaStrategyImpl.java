package br.com.GS4.wetPolicies.core.service.strategy;

import br.com.GS4.wetPolicies.core.model.entity.Proposicao;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class PoliticaMarinhaStrategyImpl implements PoliticaMarinhaStrategy {

    private static final List<String> MARINHA_KEYWORDS = Arrays.asList(
            "oceano", "mar", "rio", "lago", "água", "peixe", "tubarão", "baleia", "golfinho", "tartaruga",
            "coral", "recife", "poluição", "plástico", "microplástico", "pesca", "aquicultura", "navio",
            "navegação", "porto", "costa", "maré", "corrente", "onda", "resíduo", "água doce", "água salgada",
            "zona costeira", "biodiversidade marinha", "ecossistema aquático", "pescador", "fauna marinha",
            "flora marinha", "manguezal", "área marinha protegida", "aquecimento global", "acidificação dos oceanos"
    );


    public List<Proposicao> filtrarProposicoesPoliticaMarinha(List<Proposicao> proposicoes) {
        return proposicoes.stream()
                .filter(proposicao ->
                        possuiTermoMarinho( proposicao.getEmenta() )
                ).toList();
    }

    private boolean possuiTermoMarinho(String texto) {
        for (String keyword : MARINHA_KEYWORDS) {
            if (texto != null && texto.toLowerCase().contains(keyword)) {
                return true;
            }
        }
        return false;
    }
}