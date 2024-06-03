package br.com.GS4.wetPolicies.core.model.entity;

import br.com.GS4.wetPolicies.core.model.DTO.VotacaoDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Votacao {
    @Id
    private String id;
    private Integer idOrgao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private LocalDateTime dataHoraRegistro;

    @ManyToOne
    @JoinColumn(name = "id_proposicao")
    private Proposicao proposicao;

    public Votacao() {}

    public Votacao(VotacaoDto dto) {
        this.id = dto.id();
        this.idOrgao = dto.idOrgao();
        this.dataInicio = dto.dataInicio();
        this.dataFim = dto.dataFim();
        this.dataHoraRegistro = dto.dataHoraRegistro();
        this.proposicao = dto.proposicao();
    }
}
