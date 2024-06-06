package br.com.GS4.wetPolicies.core.model.DTO;

import br.com.GS4.wetPolicies.core.model.entity.Bancada;
import br.com.GS4.wetPolicies.core.model.entity.Deputado;
import br.com.GS4.wetPolicies.core.model.entity.Proposicao;
import br.com.GS4.wetPolicies.core.model.entity.VotacaoPorProposicao;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * DTO for {@link VotacaoPorProposicao}
 */
public record VotacaoDto(
        Integer id,
        Proposicao proposicao,
        List<Bancada> bancadas,
        List<Deputado> deputados,
        String resumo,
        String data,
        String hora,
        String objVotacao,
        String codSessao) implements Serializable {

    public VotacaoDto(VotacaoPorProposicao entity) {
        this(
                entity.getId(),
                entity.getProposicao(),
                entity.getBancadas(),
                entity.getDeputados(),
                entity.getResumo(),
                entity.getData(),
                entity.getHora(),
                entity.getObjVotacao(),
                entity.getCodSessao()
        );
    }
}