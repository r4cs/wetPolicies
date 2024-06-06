package br.com.GS4.wetPolicies.core.model.DTO;

import br.com.GS4.wetPolicies.core.model.entity.Bancada;
import br.com.GS4.wetPolicies.core.model.entity.OrientacaoBancada;
import br.com.GS4.wetPolicies.core.model.entity.Votacao;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link br.com.GS4.wetPolicies.core.model.entity.Bancada}
 */
public record BancadaDto(
        Long id,
        String nome,
        List<OrientacaoBancada> orientacoesBancadas,
        String classificacao) implements Serializable {

    public BancadaDto(Bancada entity) {
        this(
                entity.getId(),
                entity.getNome(),
                entity.getOrientacoesBancadas(),
                entity.getClassificacao()
        );
    }
}