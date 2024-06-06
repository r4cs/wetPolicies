package br.com.GS4.wetPolicies.core.model.DTO;

import br.com.GS4.wetPolicies.core.model.entity.Deputado;
import br.com.GS4.wetPolicies.core.model.entity.Votacao;

import java.io.Serializable;

/**
 * DTO for {@link Deputado}
 */

public record DeputadoDto(
        Integer ideCadastro,
        String nome,
        String partido,
        String uf,
        String classificacao,
        Votacao votacao) implements Serializable {

    public DeputadoDto(Deputado entity) {
        this(
                entity.getIdeCadastro(),
                entity.getNome(),
                entity.getPartido(),
                entity.getUf(),
                entity.getClassificacao(),
                entity.getVotacao()
        );
    }
}