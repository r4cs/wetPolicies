package br.com.GS4.wetPolicies.core.service.mappers;

import br.com.GS4.wetPolicies.core.model.DTO.VotacaoDto;
import br.com.GS4.wetPolicies.core.model.entity.Votacao;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VotacaoMapper {

    Votacao INSTANCE = Mappers.getMapper(Votacao.class);

    @BeanMapping
    VotacaoDto toDto(Votacao entity);

    @BeanMapping
    Votacao toEntity(VotacaoDto dto);
}
