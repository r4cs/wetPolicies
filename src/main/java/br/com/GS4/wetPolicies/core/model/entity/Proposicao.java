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
    private String uri;
    private String siglaTipo;
    private Integer codTipo;
    private Integer numero;
    private Integer ano;
    private String ementa;
    @ManyToOne
    @JoinColumn(name = "id_deputado")
    private Deputado idDeputadoAutor;

//    @OneToMany(mappedBy = "proposicao", cascade = CascadeType.ALL)
    @OneToMany(mappedBy = "proposicao")
    private List<VotacaoPorProposicao> votacoes;
    public Proposicao() {}

    public Proposicao(ProposicaoDto dto) {
        this.id = dto.id();
        this.uri = dto.uri();
        this.siglaTipo = dto.siglaTipo();
        this.codTipo = dto.codTipo();
        this.numero = dto.numero();
        this.ano = dto.ano();
        this.ementa = dto.ementa();
        this.idDeputadoAutor = dto.idDeputadoAutor();
        this.votacoes = dto.votacoes();
    }
}
