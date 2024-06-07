package br.com.GS4.wetPolicies.core.service.mappers;

import br.com.GS4.wetPolicies.core.model.DTO.BancadaDto;
import br.com.GS4.wetPolicies.core.model.DTO.VotacaoDto;
import br.com.GS4.wetPolicies.core.model.entity.Bancada;
import br.com.GS4.wetPolicies.core.model.entity.Votacao;
import org.springframework.stereotype.Component;

@Component
public class VotacaoMapperImpl implements  VotacaoMapper {
    @Override
    public VotacaoDto toDto(Votacao entity) {
        if (entity == null) { return null; }
        return new VotacaoDto(
                entity.getId(),
                entity.getProposicao(),
                entity.getData(),
                entity.getOrientacoesBancadas()
        );
    }

    @Override
    public Votacao toEntity(VotacaoDto dto) {
        if (dto == null) { return null; }
        return new Votacao(dto);
    }
}
