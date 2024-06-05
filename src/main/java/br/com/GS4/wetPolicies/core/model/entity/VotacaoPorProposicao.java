package br.com.GS4.wetPolicies.core.model.entity;

import br.com.GS4.wetPolicies.core.model.DTO.VotacaoDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "votacao")
public class VotacaoPorProposicao {
    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "id_proposicao")
    private Proposicao proposicao;
    @OneToMany(mappedBy = "votacao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Bancada> bancadas;
    @OneToMany(mappedBy = "votacao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Deputado> deputados;

    private String resumo;
    private Date data;
    private String hora;
    private String objVotacao;
    private String codSessao;

    public VotacaoPorProposicao() {}

    public VotacaoPorProposicao(VotacaoDto dto) {
        this.id = dto.id();
        this.proposicao = dto.proposicao();
        this.bancadas = dto.bancadas();
        this.deputados = dto.deputados();
        this.resumo = dto.resumo();
        this.data = dto.data();
        this.hora = dto.hora();
        this.objVotacao = dto.objVotacao();
        this.codSessao = dto.codSessao();
    }
}
