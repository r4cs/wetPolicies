package br.com.GS4.wetPolicies.core.service.strategy;

import br.com.GS4.wetPolicies.core.model.entity.Proposicao;

import java.util.List;

public interface PoliticaMarinhaStrategy {
    List<Proposicao> filtrarProposicoesPoliticaMarinha(List<Proposicao> proposicoes);
}
