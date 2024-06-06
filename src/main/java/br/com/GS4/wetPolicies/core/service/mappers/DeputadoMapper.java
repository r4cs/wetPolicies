package br.com.GS4.wetPolicies.core.service.mappers;

import br.com.GS4.wetPolicies.core.model.DTO.DeputadoDto;
import br.com.GS4.wetPolicies.core.model.entity.Deputado;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeputadoMapper {

    Deputado INSTANCE = Mappers.getMapper(Deputado.class);

    @BeanMapping
    DeputadoDto toDto(Deputado entity);

    @BeanMapping
    Deputado toEntity(DeputadoDto dto);
}
