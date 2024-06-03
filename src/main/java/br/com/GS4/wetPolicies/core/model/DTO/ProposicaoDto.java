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
        String uri,
        String siglaTipo,
        Integer codTipo,
        Integer numero,
        Integer ano,
        String ementa,
        Deputado idDeputadoAutor,
        List<Votacao> votacoes) implements Serializable {
    public ProposicaoDto(Proposicao entity) {
        this(
                entity.getId(),
                entity.getUri(),
                entity.getSiglaTipo(),
                entity.getCodTipo(),
                entity.getNumero(),
                entity.getAno(),
                entity.getEmenta(),
                entity.getIdDeputadoAutor(),
                entity.getVotacoes()
        );
    }
}