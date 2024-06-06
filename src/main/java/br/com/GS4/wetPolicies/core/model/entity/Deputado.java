package br.com.GS4.wetPolicies.core.model.entity;


import br.com.GS4.wetPolicies.core.model.DTO.DeputadoDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "deputado")
public class Deputado {
    @Id
    private Integer ideCadastro;
    private String nome;
    private String partido;
    private String uf;
    private String classificacao;
    @ManyToOne
    @JoinColumn(name = "id_votacao")
    private Votacao votacao;
    public Deputado() {}

    public Deputado(DeputadoDto dto) {
        this.ideCadastro = dto.ideCadastro();
        this.nome = dto.nome();
        this.partido = dto.partido();
        this.uf = dto.uf();
        this.classificacao = dto.classificacao();
        this.votacao = dto.votacao();
    }

}