package br.com.GS4.wetPolicies.core.model.DTO;

import br.com.GS4.wetPolicies.core.model.entity.Deputado;
import br.com.GS4.wetPolicies.core.model.entity.Proposicao;
import br.com.GS4.wetPolicies.core.model.entity.Votacao;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Proposicao}
 */
public record ProposicaoDto(
        Integer id,
        String siglaTipo,
        Integer numero,
        Integer ano,
        String ementa) implements Serializable {
    public ProposicaoDto(Proposicao entity) {
        this(
                entity.getId(),
                entity.getSiglaTipo(),
                entity.getNumero(),
                entity.getAno(),
                entity.getEmenta()
        );
    }
}