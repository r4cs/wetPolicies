package br.com.GS4.wetPolicies.core.model.DTO;

import br.com.GS4.wetPolicies.core.model.entity.Bancada;
import br.com.GS4.wetPolicies.core.model.entity.VotacaoPorProposicao;

import java.io.Serializable;

/**
 * DTO for {@link br.com.GS4.wetPolicies.core.model.entity.Bancada}
 */
public record BancadaDto(
        Integer id,
        String sigla,
        String orientacao,
        VotacaoPorProposicao votacao) implements Serializable {

    public BancadaDto(Bancada entity) {
        this(
                entity.getId(),
                entity.getSigla(),
                entity.getOrientacao(),
                entity.getVotacao()
        );
    }
}