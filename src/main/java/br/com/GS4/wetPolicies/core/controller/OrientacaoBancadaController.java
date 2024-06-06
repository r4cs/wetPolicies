package br.com.GS4.wetPolicies.core.controller;

import br.com.GS4.wetPolicies.core.model.entity.Bancada;
import br.com.GS4.wetPolicies.core.model.entity.OrientacaoBancada;
import br.com.GS4.wetPolicies.core.service.BancadaService;
import br.com.GS4.wetPolicies.core.service.strategy.OrientacaoBancadaService;
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
@RequestMapping("/api/orientacao-bancada")
public class OrientacaoBancadaController {

    private final OrientacaoBancadaService orientacaoBancadaService;

    public OrientacaoBancadaController(OrientacaoBancadaService orientacaoBancadaService) {
        this.orientacaoBancadaService = orientacaoBancadaService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<OrientacaoBancada>> getAllBancadas(
        @RequestParam(required = false, defaultValue = "0") Integer page,
        @RequestParam(required = false, defaultValue = "10") Integer size,
        @Parameter(description = "Atributo para ordenação. Opções: id, id_legislatura, nome, sigla do partido, sigla do sexo, sigla do Estado, classificacao (verde, amarelo, vermelho)")
        @RequestParam(required = false, defaultValue = "id") String orderBy) {

            Pageable defaultPageable = PageRequest.of(page, size, Sort.by(orderBy));
            Page<OrientacaoBancada> bancada = orientacaoBancadaService.findAll(defaultPageable);
            return ResponseEntity.ok(bancada);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<OrientacaoBancada>> getBancadaById(@PathVariable Integer id) {
        Optional<OrientacaoBancada> bancada = orientacaoBancadaService.findById(id);
        return ResponseEntity.ok(bancada);
    }

//    @Hidden
    @PostMapping
    public ResponseEntity<OrientacaoBancada> saveBancada(@RequestBody OrientacaoBancada orientacaoBancada) {
        OrientacaoBancada savedBancada = orientacaoBancadaService.save(orientacaoBancada);
        return ResponseEntity.ok(savedBancada);
    }

//    @Hidden
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBancada(@PathVariable Integer id) {
        orientacaoBancadaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}