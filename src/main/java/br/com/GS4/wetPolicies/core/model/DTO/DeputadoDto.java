package br.com.GS4.wetPolicies.core.model.DTO;

import br.com.GS4.wetPolicies.core.model.entity.Deputado;
import br.com.GS4.wetPolicies.core.model.entity.Proposicao;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Deputado}
 */
public record DeputadoDto(
        Integer id,
        String nome,
        Integer idLegislatura,
        String siglaUf,
        String siglaPartido,
        String siglaSexo,
        List<Proposicao> proposicoes) implements Serializable {

    public DeputadoDto(Deputado entity) {
        this(
                entity.getId(),
                entity.getNome(),
                entity.getIdLegislatura(),
                entity.getSiglaUf(),
                entity.getSiglaPartido(),
                entity.getSiglaSexo(),
                entity.getProposicoes()
        );
    }
}