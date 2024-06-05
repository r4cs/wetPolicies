package br.com.GS4.wetPolicies.core.model.entity;

import br.com.GS4.wetPolicies.core.model.DTO.BancadaDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bancada")
public class Bancada {

    @Id
    private Long id;

    private String sigla;
    private String orientacao;

    @ManyToOne
    @JoinColumn(name = "id_votacao")
    private VotacaoPorProposicao votacao;

    public Bancada() {}

    public Bancada(BancadaDto dto) {
        this.id = dto.id();
        this.sigla = dto.sigla();
        this.orientacao = dto.orientacao();
        this.votacao = dto.votacao();
    }
}
