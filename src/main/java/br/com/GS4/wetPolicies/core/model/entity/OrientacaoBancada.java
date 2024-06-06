package br.com.GS4.wetPolicies.core.model.entity;

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

}
