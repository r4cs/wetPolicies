package br.com.GS4.wetPolicies.core.service.mappers;

import br.com.GS4.wetPolicies.core.model.DTO.BancadaDto;
import br.com.GS4.wetPolicies.core.model.DTO.DeputadoDto;
import br.com.GS4.wetPolicies.core.model.entity.Bancada;
import br.com.GS4.wetPolicies.core.model.entity.Deputado;
import org.springframework.stereotype.Component;

@Component
public class DeputadoMapperImpl implements  DeputadoMapper {
    @Override
    public DeputadoDto toDto(Deputado entity) {
        if (entity == null) { return null; }
        return new DeputadoDto(
                entity.getIdeCadastro(),
                entity.getNome(),
                entity.getUf(),
                entity.getClassificacao(),
                entity.getPartido(),
                entity.getVotacao()
        );
    }

    @Override
    public Deputado toEntity(DeputadoDto dto) {
        return new Deputado(dto);
    }
}
