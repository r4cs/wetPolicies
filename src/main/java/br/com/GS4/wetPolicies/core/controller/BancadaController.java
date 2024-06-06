package br.com.GS4.wetPolicies.core.controller;

import br.com.GS4.wetPolicies.core.model.DTO.BancadaDto;
import br.com.GS4.wetPolicies.core.model.entity.Bancada;
import br.com.GS4.wetPolicies.core.service.BancadaService;
import br.com.GS4.wetPolicies.core.service.mappers.BancadaMapperImpl;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/bancada")
public class BancadaController {

    private final BancadaService bancadaService;
    private final BancadaMapperImpl mapper;

    public BancadaController(BancadaService bancadaService, BancadaMapperImpl mapper) {
        this.bancadaService = bancadaService;
        this.mapper = mapper;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<BancadaDto>> getAllBancadas(
        @RequestParam(required = false, defaultValue = "0") Integer page,
        @RequestParam(required = false, defaultValue = "10") Integer size,
        @Parameter(description = "Atributo para ordenação. Opções: id, id_legislatura, nome, sigla do partido, sigla do sexo, sigla do Estado, classificacao (verde, amarelo, vermelho)")
        @RequestParam(required = false, defaultValue = "id") String orderBy) {

            Pageable defaultPageable = PageRequest.of(page, size, Sort.by(orderBy));
            Page<Bancada> bancada = bancadaService.findAll(defaultPageable);
            Page<BancadaDto> bancadaDTOPage = bancada.map(mapper::toDto);
            return ResponseEntity.ok(bancadaDTOPage);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BancadaDto> getBancadaById(@PathVariable Integer id) {
        Optional<Bancada> bancada = bancadaService.findById(id);
        return bancada.map(value -> ResponseEntity.ok(mapper.toDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

//    @Hidden
    @PostMapping
    public ResponseEntity<BancadaDto> saveBancada(@RequestBody BancadaDto bancadaDTO) {
        Bancada bancada = mapper.toEntity(bancadaDTO);
        Bancada savedBancada = bancadaService.save(bancada);
        BancadaDto savedBancadaDto = mapper.toDto(savedBancada);
        return ResponseEntity.ok(savedBancadaDto);
    }

//    @Hidden
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBancada(@PathVariable Integer id) {
        bancadaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}