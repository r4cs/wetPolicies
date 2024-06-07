package br.com.GS4.wetPolicies.core.model.entity;

import br.com.GS4.wetPolicies.core.model.DTO.VotacaoDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "votacao")
public class Votacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_proposicao")
    private Proposicao proposicao;

    private String data;

    @OneToMany(mappedBy = "votacao")
    private List<OrientacaoBancada> orientacoesBancadas;

//    // Relacionamento com Votos de Deputados
//    @OneToMany(mappedBy = "votacao")
//    private List<VotoDeputado> votosDeputados;


    public Votacao() {};
    public Votacao(VotacaoDto dto) {
        this.id = dto.id();
        this.proposicao = dto.proposicao();
        this.data = dto.data();
//        this.orientacoesBancadas = dto.orientacoesBancadas();
    }
}
