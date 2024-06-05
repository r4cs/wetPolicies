package br.com.GS4.wetPolicies.core.service.mappers;

import br.com.GS4.wetPolicies.core.model.DTO.ProposicaoDto;
import br.com.GS4.wetPolicies.core.model.entity.Proposicao;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProposicaoMapper {

    Proposicao INSTANCE = Mappers.getMapper(Proposicao.class);

    @BeanMapping
    ProposicaoDto toDto(Proposicao entity);

    @BeanMapping
    Proposicao toEntity(ProposicaoDto dto);
}
