package br.com.GS4.wetPolicies.core.controller;

import br.com.GS4.wetPolicies.core.model.entity.Bancada;
import br.com.GS4.wetPolicies.core.service.BancadaService;
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

    public BancadaController(BancadaService bancadaService) {
        this.bancadaService = bancadaService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Bancada>> getAllBancadas(
        @RequestParam(required = false, defaultValue = "0") Integer page,
        @RequestParam(required = false, defaultValue = "10") Integer size,
        @Parameter(description = "Atributo para ordenação. Opções: id, id_legislatura, nome, sigla do partido, sigla do sexo, sigla do Estado, classificacao (verde, amarelo, vermelho)")
        @RequestParam(required = false, defaultValue = "id") String orderBy) {

            Pageable defaultPageable = PageRequest.of(page, size, Sort.by(orderBy));
            Page<Bancada> bancada = bancadaService.findAll(defaultPageable);
            return ResponseEntity.ok(bancada);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Bancada>> getBancadaById(@PathVariable Integer id) {
        Optional<Bancada> bancada = bancadaService.findById(id);
        return ResponseEntity.ok(bancada);
    }

//    @Hidden
    @PostMapping
    public ResponseEntity<Bancada> saveBancada(@RequestBody Bancada bancada) {
        Bancada savedBancada = bancadaService.save(bancada);
        return ResponseEntity.ok(savedBancada);
    }

//    @Hidden
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBancada(@PathVariable Integer id) {
        bancadaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}