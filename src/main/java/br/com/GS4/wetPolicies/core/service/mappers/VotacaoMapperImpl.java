package br.com.GS4.wetPolicies.core.service.mappers;

import br.com.GS4.wetPolicies.core.model.DTO.BancadaDto;
import br.com.GS4.wetPolicies.core.model.entity.Bancada;
import org.springframework.stereotype.Component;

@Component
public class VotacaoMapperImpl implements  BancadaMapper {
    @Override
    public BancadaDto toDto(Bancada entity) {
        if (entity == null) { return null; }
        return new BancadaDto(
                entity.getId(),
                entity.getNome(),
                entity.getOrientacoesBancadas(),
                entity.getClassificacao()
        );
    }

    @Override
    public Bancada toEntity(BancadaDto dto) {
        return null;
    }
}
