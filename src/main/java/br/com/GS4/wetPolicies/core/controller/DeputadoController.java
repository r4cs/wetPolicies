package br.com.GS4.wetPolicies.core.controller;

import br.com.GS4.wetPolicies.core.model.DTO.DeputadoDto;
import br.com.GS4.wetPolicies.core.model.entity.Deputado;
import br.com.GS4.wetPolicies.core.service.DeputadoService;
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
    public ResponseEntity<List<DeputadoDto>> getAllDeputados() {
        List<DeputadoDto> deputados = deputadoService.findAll();
        return ResponseEntity.ok(deputados);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Deputado>> getDeputadoById(@PathVariable Integer id) {
        Optional<Deputado> deputado = deputadoService.findById(id);
        return ResponseEntity.ok(deputado);
    }

    @PostMapping
    public ResponseEntity<Deputado> saveDeputado(@RequestBody Deputado deputado) {
        Deputado savedDeputado = deputadoService.save(deputado);
        return ResponseEntity.ok(savedDeputado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeputado(@PathVariable Integer id) {
        deputadoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}