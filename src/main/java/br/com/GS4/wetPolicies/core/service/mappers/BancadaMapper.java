package br.com.GS4.wetPolicies.core.service.mappers;

import br.com.GS4.wetPolicies.core.model.DTO.BancadaDto;
import br.com.GS4.wetPolicies.core.model.entity.Bancada;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BancadaMapper {

    Bancada INSTANCE = Mappers.getMapper(Bancada.class);

    @BeanMapping
    BancadaDto toDto(Bancada entity);

    @BeanMapping
    Bancada toEntity(BancadaDto dto);
}
