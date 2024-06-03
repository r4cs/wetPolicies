package br.com.GS4.wetPolicies.core.model.DTO;

import br.com.GS4.wetPolicies.core.model.entity.Proposicao;
import br.com.GS4.wetPolicies.core.model.entity.Votacao;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO for {@link Votacao}
 */
public record VotacaoDto(
        String id,
        Integer idOrgao,
        LocalDate dataInicio,
        LocalDate dataFim,
        LocalDateTime dataHoraRegistro,
        Proposicao proposicao) implements Serializable {

    public VotacaoDto(Votacao entity) {
        this(
                entity.getId(),
                entity.getIdOrgao(),
                entity.getDataInicio(),
                entity.getDataFim(),
                entity.getDataHoraRegistro(),
                entity.getProposicao()
        );
    }
}