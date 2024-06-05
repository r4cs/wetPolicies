package br.com.GS4.wetPolicies.core.controller;

import br.com.GS4.wetPolicies.core.model.DTO.ProposicaoDto;
import br.com.GS4.wetPolicies.core.model.entity.Proposicao;
import br.com.GS4.wetPolicies.core.service.ProposicaoService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proposicoes")
public class ProposicaoController {

    private final ProposicaoService proposicaoService;

    public ProposicaoController(ProposicaoService proposicaoService) {
        this.proposicaoService = proposicaoService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Proposicao> getAllProposicoes(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @Parameter(description = "Atributo para ordenação. Opções: id, uri, siglaTipo, codTipo,numero, ano, ementa")
            @RequestParam(required = false, defaultValue = "id") String orderBy) {
        return proposicaoService.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Proposicao> getProposicaoById(@PathVariable Integer id) {
        return proposicaoService.findById(id);
    }
}