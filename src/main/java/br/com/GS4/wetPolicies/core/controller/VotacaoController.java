package br.com.GS4.wetPolicies.core.controller;

import br.com.GS4.wetPolicies.core.model.DTO.VotacaoDto;
import br.com.GS4.wetPolicies.core.model.entity.VotacaoPorProposicao;
import br.com.GS4.wetPolicies.core.service.VotacaoPorProposicaoService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/votacoes")
public class VotacaoController {

    private final VotacaoPorProposicaoService votacaoPorProposicaoService;

    public VotacaoController(VotacaoPorProposicaoService votacaoPorProposicaoService) {
        this.votacaoPorProposicaoService = votacaoPorProposicaoService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VotacaoDto> getAllVotacoes(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @Parameter(description = "Atributo para ordenação. Opções: id, idOrgao, dataInicio, dataFim")
            @RequestParam(required = false, defaultValue = "id") String orderBy) {
        return votacaoPorProposicaoService.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<VotacaoPorProposicao> getVotacaoById(@PathVariable String id) {
        return votacaoPorProposicaoService.findById(id);
    }
}