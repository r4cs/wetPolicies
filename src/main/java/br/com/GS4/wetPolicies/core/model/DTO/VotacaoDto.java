package br.com.GS4.wetPolicies.core.model.DTO;

import br.com.GS4.wetPolicies.core.model.entity.*;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Votacao}
 */
public record VotacaoDto(
        Long id,
        Proposicao proposicao,
        String data,
        List<OrientacaoBancada> orientacoesBancadas) implements Serializable {

    public VotacaoDto(Votacao entity) {
        this(
                entity.getId(),
                entity.getProposicao(),
                entity.getData(),
                entity.getOrientacoesBancadas()
        );
    }
}