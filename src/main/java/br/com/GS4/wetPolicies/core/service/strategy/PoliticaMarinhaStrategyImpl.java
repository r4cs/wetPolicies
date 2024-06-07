package br.com.GS4.wetPolicies.core.service.strategy;

import br.com.GS4.wetPolicies.core.model.entity.Proposicao;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class PoliticaMarinhaStrategyImpl implements PoliticaMarinhaStrategy {

    private static final List<String> MARINHA_KEYWORDS = Arrays.asList(
            "poluição do oceano", "resíduos de plástico no oceano",
            "recursos hídricos", "fiscalização de portos",
            "fauna aquática", "resíduos na água", "resíduos no oceano",
            "biodiversidade marinha", "ecossistema aquático", "navio-pesqueiro",
            "fauna marinha", "flora marinha", "área marinha protegida",
            "aquecimento global", "acidificação dos oceanos", "zona costeira",
            "crimes ambientais", "recurso pesqueiro", "convenção sobre o direito do mar",
            "reservas marinhas", "áreas protegidas", "unidades de conservação"
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