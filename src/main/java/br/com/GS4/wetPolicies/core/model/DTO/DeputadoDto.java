package br.com.GS4.wetPolicies.core.model.DTO;

import br.com.GS4.wetPolicies.core.model.entity.Deputado;
import br.com.GS4.wetPolicies.core.model.entity.Proposicao;
import br.com.GS4.wetPolicies.core.model.entity.VotacaoPorProposicao;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Deputado}
 */

public record DeputadoDto(
        Integer id,
        String nome,
        String ideCadastro,
        String partido,
        String uf,
        String classificacao,
        String voto,
        VotacaoPorProposicao votacao) implements Serializable {

    public DeputadoDto(Deputado entity) {
        this(
                entity.getId(),
                entity.getNome(),
                entity.getIdeCadastro(),
                entity.getPartido(),
                entity.getUf(),
                entity.getClassificacao(),
                entity.getVoto(),
                entity.getVotacao()
        );
    }
}