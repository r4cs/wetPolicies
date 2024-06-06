package br.com.GS4.wetPolicies.core.service.mappers;

import br.com.GS4.wetPolicies.core.model.DTO.ProposicaoDto;
import br.com.GS4.wetPolicies.core.model.entity.Proposicao;
import org.springframework.stereotype.Component;

@Component
public class ProposicaoMapperImpl implements  ProposicaoMapper {
    @Override
    public ProposicaoDto toDto(Proposicao entity) {
        if (entity == null) { return null; }
        return new ProposicaoDto(
                entity.getId(),
                entity.getSiglaTipo(),
                entity.getNumero(),
                entity.getAno(),
                entity.getEmenta(),
                entity.getVotacoes()
        );
    }

    @Override
    public Proposicao toEntity(ProposicaoDto dto) {
        return null;
    }
}
