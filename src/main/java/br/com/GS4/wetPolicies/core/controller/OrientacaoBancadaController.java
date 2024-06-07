package br.com.GS4.wetPolicies.core.controller;

import br.com.GS4.wetPolicies.core.model.DTO.OrientacaoBancadaDto;
import br.com.GS4.wetPolicies.core.model.entity.OrientacaoBancada;
import br.com.GS4.wetPolicies.core.repository.BancadaRepository;
import br.com.GS4.wetPolicies.core.service.OrientacaoBancadaService;
import br.com.GS4.wetPolicies.core.service.mappers.OrientacaoBancadaMapperImpl;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/orientacao-bancada")
public class OrientacaoBancadaController {

    private final OrientacaoBancadaService service;
    private final OrientacaoBancadaMapperImpl mapper;
    private final BancadaRepository bancadaRepository;

    public OrientacaoBancadaController(OrientacaoBancadaService service, OrientacaoBancadaMapperImpl mapper,
                                       BancadaRepository bancadaRepository) {
        this.service = service;
        this.mapper = mapper;
        this.bancadaRepository = bancadaRepository;
    }

    @GetMapping
    public ResponseEntity<Page<OrientacaoBancadaDto>> getAllBancadas(
        @RequestParam(required = false, defaultValue = "0") Integer page,
        @RequestParam(required = false, defaultValue = "10") Integer size,
        @Parameter(description = "Atributo para ordenação. Opções: id, id_legislatura, nome, sigla do partido, sigla do sexo, sigla do Estado, classificacao (verde, amarelo, vermelho)")
        @RequestParam(required = false, defaultValue = "id") String orderBy) {

            Pageable defaultPageable = PageRequest.of(page, size, Sort.by(orderBy));
            Page<OrientacaoBancada> orientacaoBancadas = service.findAll(defaultPageable);
            Page<OrientacaoBancadaDto> orientacaoBancadasDTOPage = orientacaoBancadas.map(mapper::toDto);
            return ResponseEntity.ok(orientacaoBancadasDTOPage);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrientacaoBancadaDto> getBancadaById(@PathVariable Integer id) {
        Optional<OrientacaoBancada> orientacaoBancada = service.findById(id);
        return orientacaoBancada.map(value -> ResponseEntity.ok(mapper.toDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

//    @Hidden
    @PostMapping
    public ResponseEntity<OrientacaoBancadaDto> saveOrientacaoBancada(@RequestBody OrientacaoBancadaDto dto) {
        OrientacaoBancada orientacaoBancada = mapper.toEntity(dto);
        OrientacaoBancada savedOrientacaoBancada = service.save(orientacaoBancada);
        OrientacaoBancadaDto savedDto = mapper.toDto(savedOrientacaoBancada);
        return ResponseEntity.ok(savedDto);
    }

    //    @Hidden
    @PatchMapping("/{id}")
    public ResponseEntity<OrientacaoBancadaDto> update(@PathVariable Integer id, @RequestBody OrientacaoBancadaDto dto) {
        Optional<OrientacaoBancada> updatedOrientacaoBancada = service.update(id, dto);
        return updatedOrientacaoBancada.map(value -> ResponseEntity.ok(mapper.toDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

//    @Hidden
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBancada(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}