package br.com.GS4.wetPolicies.core.model.entity;

import br.com.GS4.wetPolicies.core.model.DTO.ProposicaoDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "proposicao")
public class Proposicao {
    @Id
    private Integer id;
    private String siglaTipo;
    private Integer numero;
    private Integer ano;
    private String ementa;

    @OneToMany(mappedBy = "proposicao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Votacao> votacoes;

    public Proposicao() {}

    public Proposicao(ProposicaoDto dto) {
        this.id = dto.id();
        this.siglaTipo = dto.siglaTipo();
        this.numero = dto.numero();
        this.ano = dto.ano();
        this.ementa = dto.ementa();
        this.votacoes = dto.votacoes();
    }
}
