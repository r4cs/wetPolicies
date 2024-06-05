package br.com.GS4.wetPolicies.core.controller;

import br.com.GS4.wetPolicies.core.model.DTO.DeputadoDto;
import br.com.GS4.wetPolicies.core.model.entity.Deputado;
import br.com.GS4.wetPolicies.core.service.DeputadoService;
import io.swagger.v3.oas.annotations.Hidden;
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
@RequestMapping("/api/deputados")
public class DeputadoController {

    private final DeputadoService deputadoService;

    public DeputadoController(DeputadoService deputadoService) {
        this.deputadoService = deputadoService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Deputado>> getAllDeputados(
        @RequestParam(required = false, defaultValue = "0") Integer page,
        @RequestParam(required = false, defaultValue = "10") Integer size,
        @Parameter(description = "Atributo para ordenação. Opções: id, id_legislatura, nome, sigla do partido, sigla do sexo, sigla do Estado, classificacao (verde, amarelo, vermelho)")
        @RequestParam(required = false, defaultValue = "id") String orderBy) {

            Pageable defaultPageable = PageRequest.of(page, size, Sort.by(orderBy));
            Page<Deputado> deputados = deputadoService.findAll(defaultPageable);
            return ResponseEntity.ok(deputados);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Deputado>> getDeputadoById(@PathVariable Integer id) {
        Optional<Deputado> deputado = deputadoService.findById(id);
        return ResponseEntity.ok(deputado);
    }

//    @Hidden
    @PostMapping
    public ResponseEntity<Deputado> saveDeputado(@RequestBody Deputado deputado) {
        Deputado savedDeputado = deputadoService.save(deputado);
        return ResponseEntity.ok(savedDeputado);
    }

//    @Hidden
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeputado(@PathVariable Integer id) {
        deputadoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}