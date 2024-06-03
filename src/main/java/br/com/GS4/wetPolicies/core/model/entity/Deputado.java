package br.com.GS4.wetPolicies.core.model.entity;


import br.com.GS4.wetPolicies.core.model.DTO.DeputadoDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Deputado {
    @Id
    private Integer id;
    private String nome;
    private Integer idLegislatura;
    private String siglaUf;
    private String siglaPartido;
    private String siglaSexo;
    @OneToMany(mappedBy = "idDeputadoAutor")
    private List<Proposicao> proposicoes;
    private String classificacao;
    public Deputado() {}

    public Deputado(DeputadoDto dto) {
        this.id = dto.id();
        this.nome = dto.nome();
        this.idLegislatura = dto.idLegislatura();
        this.siglaUf = dto.siglaUf();
        this.siglaPartido = dto.siglaPartido();
        this.siglaSexo = dto.siglaSexo();
        this.proposicoes = dto.proposicoes();
    }

}
