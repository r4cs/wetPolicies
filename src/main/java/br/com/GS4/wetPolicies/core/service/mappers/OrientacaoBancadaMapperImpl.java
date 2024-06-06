package br.com.GS4.wetPolicies.core.service.mappers;

import br.com.GS4.wetPolicies.core.model.DTO.OrientacaoBancadaDto;
import br.com.GS4.wetPolicies.core.model.entity.OrientacaoBancada;
import org.springframework.stereotype.Component;

@Component
public class OrientacaoBancadaMapperImpl implements  OrientacaoBancadaMapper {
    @Override
    public OrientacaoBancadaDto toDto(OrientacaoBancada entity) {
        if (entity == null) { return null; }
        return new OrientacaoBancadaDto(
                entity.getId(),
                entity.getVotacao(),
                entity.getBancada(),
                entity.getOrientacao()
        );
    }

    @Override
    public OrientacaoBancada toEntity(OrientacaoBancadaDto dto) {
        return null;
    }
}
