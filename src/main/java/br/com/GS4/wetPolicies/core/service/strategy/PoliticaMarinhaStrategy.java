package br.com.GS4.wetPolicies.core.service.strategy;

import br.com.GS4.wetPolicies.core.model.DTO.DeputadoDto;
import br.com.GS4.wetPolicies.core.model.DTO.ProposicaoDto;
import br.com.GS4.wetPolicies.core.model.DTO.VotacaoDto;

import java.util.List;

public interface PoliticaMarinhaStrategy {
    List<DeputadoDto> filtrarDeputadosPoliticaMarinha(List<DeputadoDto> deputados);
    List<ProposicaoDto> filtrarProposicoesPoliticaMarinha(List<ProposicaoDto> proposicoes);
    List<VotacaoDto> filtrarVotacoesPoliticaMarinha(List<VotacaoDto> votacoes);
}
