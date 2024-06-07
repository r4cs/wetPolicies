package br.com.GS4.wetPolicies.core.controller;

import br.com.GS4.wetPolicies.core.model.DTO.BancadaDto;
import br.com.GS4.wetPolicies.core.model.DTO.ProposicaoDto;
import br.com.GS4.wetPolicies.core.model.entity.Proposicao;
import br.com.GS4.wetPolicies.core.service.ProposicaoService;
import br.com.GS4.wetPolicies.core.service.mappers.ProposicaoMapperImpl;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/proposicoes")
public class ProposicaoController {

    private final ProposicaoService service;
    private final ProposicaoMapperImpl mapper;

    public ProposicaoController(ProposicaoService service, ProposicaoMapperImpl mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<Page<ProposicaoDto>> getAllProposicoes(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @Parameter(description = "Atributo para ordenação. Opções: id, uri, siglaTipo, codTipo,numero, ano, ementa")
            @RequestParam(required = false, defaultValue = "id") String orderBy) {

        Pageable defaultPageable = PageRequest.of(page, size, Sort.by(orderBy));
        Page<Proposicao> proposicoes = service.findAll(defaultPageable);
        Page<ProposicaoDto> proposicoesDTOPage = proposicoes.map(mapper::toDto);
        return ResponseEntity.ok(proposicoesDTOPage);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProposicaoDto> getProposicaoById(@PathVariable Integer id) {
        Optional<Proposicao> proposicao = service.findById(id);
        return proposicao.map(value -> ResponseEntity.ok(mapper.toDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProposicaoDto> save(@RequestBody ProposicaoDto dto) {
        Proposicao proposicao = mapper.toEntity(dto);
        Proposicao savedProposicao = service.save(proposicao);
        ProposicaoDto savedDto = mapper.toDto(savedProposicao);
        return ResponseEntity.ok(savedDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}