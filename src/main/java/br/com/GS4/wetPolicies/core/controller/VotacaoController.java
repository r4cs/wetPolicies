package br.com.GS4.wetPolicies.core.controller;

import br.com.GS4.wetPolicies.core.model.DTO.VotacaoDto;
import br.com.GS4.wetPolicies.core.model.DTO.VotacaoDto;
import br.com.GS4.wetPolicies.core.model.entity.Votacao;
import br.com.GS4.wetPolicies.core.model.entity.Votacao;
import br.com.GS4.wetPolicies.core.service.VotacaoService;
import br.com.GS4.wetPolicies.core.service.mappers.VotacaoMapperImpl;
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
@RequestMapping("/api/votacoes")
public class VotacaoController {

    private final VotacaoService service;
    private final VotacaoMapperImpl mapper;

    public VotacaoController(VotacaoService service, VotacaoMapperImpl mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<Page<VotacaoDto>> getAllVotacoes(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @Parameter(description = "Atributo para ordenação. Opções: id, idOrgao, dataInicio, dataFim")
            @RequestParam(required = false, defaultValue = "id") String orderBy) {

        Pageable defaultPageable = PageRequest.of(page,size, Sort.by(orderBy));
        Page<Votacao> votacao = service.findAll(defaultPageable);
        Page<VotacaoDto> votacaoDtoPage = votacao.map(mapper::toDto);
        return ResponseEntity.ok(votacaoDtoPage);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<VotacaoDto> getVotacaoById(@PathVariable Integer id) {
        Optional<Votacao> votacao = service.findById(id);
        return votacao.map(value -> ResponseEntity.ok(mapper.toDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //    @Hidden
    @PostMapping
    public ResponseEntity<VotacaoDto> save(@RequestBody VotacaoDto dto) {
        Votacao votacao = mapper.toEntity(dto);
        Votacao savedVotacao = service.save(votacao);
        VotacaoDto savedVotacaoDto = mapper.toDto(savedVotacao);
        return ResponseEntity.ok(savedVotacaoDto);
    }

    //    @Hidden
    @PatchMapping("/{id}")
    public ResponseEntity<VotacaoDto> update(@PathVariable Integer id, @RequestBody VotacaoDto dto) {
        Optional<Votacao> updatedVotacao = service.update(id, dto);
        return updatedVotacao.map(value -> ResponseEntity.ok(mapper.toDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //    @Hidden
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
















