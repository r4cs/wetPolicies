package br.com.GS4.wetPolicies.core.model.entity;

import br.com.GS4.wetPolicies.core.model.DTO.BancadaDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "bancada")
public class Bancada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "bancada", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrientacaoBancada> orientacoesBancadas;

    private String classificacao;

    public Bancada() {}

    public Bancada(BancadaDto dto) {
        this.id = dto.id();
        this.nome = dto.nome();
        this.orientacoesBancadas = dto.orientacoesBancadas();
        this.classificacao = dto.classificacao();
    }
}
