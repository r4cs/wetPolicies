package br.com.GS4.wetPolicies.core.model.DTO;

import br.com.GS4.wetPolicies.core.model.entity.Bancada;
import br.com.GS4.wetPolicies.core.model.entity.OrientacaoBancada;
import br.com.GS4.wetPolicies.core.model.entity.Votacao;

import java.io.Serializable;

/**
 * DTO for {@link br.com.GS4.wetPolicies.core.model.entity.OrientacaoBancada}
 */
public record OrientacaoBancadaDto(
        Long id,
        Votacao votacao,
        Bancada bancada,
        String orientacao) implements Serializable {

    public OrientacaoBancadaDto(OrientacaoBancada entity) {
        this(
                entity.getId(),
                entity.getVotacao(),
                entity.getBancada(),
                entity.getOrientacao()
        );
    }
}