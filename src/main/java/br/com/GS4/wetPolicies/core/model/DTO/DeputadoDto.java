package br.com.GS4.wetPolicies.core.model.DTO;

import br.com.GS4.wetPolicies.core.model.entity.Deputado;
import br.com.GS4.wetPolicies.core.model.entity.Votacao;

import java.io.Serializable;

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
        Votacao votacao) implements Serializable {

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