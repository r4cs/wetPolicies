package br.com.GS4.wetPolicies.core.model.entity;

import br.com.GS4.wetPolicies.core.model.DTO.OrientacaoBancadaDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orientacao_bancada")
public class OrientacaoBancada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_votacao")
    private Votacao votacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bancada")
    private Bancada bancada;

    private String orientacao; // 'Sim', 'Não', 'Abstenção', etc.

    public OrientacaoBancada() {}
    public OrientacaoBancada(OrientacaoBancadaDto dto) {
        this.id = dto.id();
        this.votacao = dto.votacao();
        this.bancada = dto.bancada();
        this.orientacao = dto.orientacao();
    }

}
