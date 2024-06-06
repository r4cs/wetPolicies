package br.com.GS4.wetPolicies.core.service.mappers;

import br.com.GS4.wetPolicies.core.model.DTO.OrientacaoBancadaDto;
import br.com.GS4.wetPolicies.core.model.entity.OrientacaoBancada;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrientacaoBancadaMapper {

    OrientacaoBancada INSTANCE = Mappers.getMapper(OrientacaoBancada.class);

    @BeanMapping
    OrientacaoBancadaDto toDto(OrientacaoBancada entity);

    @BeanMapping
    OrientacaoBancada toEntity(OrientacaoBancadaDto dto);
}
