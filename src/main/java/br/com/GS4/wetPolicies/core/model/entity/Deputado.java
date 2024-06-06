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
    private Integer id;
    private String nome;
    private String ideCadastro;
    private String partido;
    private String uf;
    private String classificacao;
    private String voto;
    @ManyToOne
    @JoinColumn(name = "id_votacao")
    private Votacao votacao;
    public Deputado() {}

    public Deputado(DeputadoDto dto) {
        this.id = dto.id();
        this.nome = dto.nome();
        this.ideCadastro = dto.ideCadastro();
        this.partido = dto.partido();
        this.uf = dto.uf();
        this.classificacao = dto.classificacao();
        this.voto = dto.voto();
        this.votacao = dto.votacao();
    }

}