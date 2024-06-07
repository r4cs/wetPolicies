package br.com.GS4.wetPolicies.core.controller;

import br.com.GS4.wetPolicies.core.model.DTO.DeputadoDto;
import br.com.GS4.wetPolicies.core.model.entity.Deputado;
import br.com.GS4.wetPolicies.core.service.DeputadoService;
import br.com.GS4.wetPolicies.core.service.mappers.DeputadoMapperImpl;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController(value="deputado")
@RequestMapping("/api/deputados")
@Hidden
public class DeputadoController {

    private final DeputadoService service;
    private final DeputadoMapperImpl mapper;

    public DeputadoController(DeputadoService service, DeputadoMapperImpl mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<Page<DeputadoDto>> getAllDeputados(
        @RequestParam(required = false, defaultValue = "0") Integer page,
        @RequestParam(required = false, defaultValue = "10") Integer size,
        @Parameter(description = "Atributo para ordenação. Opções: id, id_legislatura, nome, sigla do partido, sigla do sexo, sigla do Estado, classificacao (verde, amarelo, vermelho)")
        @RequestParam(required = false, defaultValue = "id") String orderBy) {

            Pageable defaultPageable = PageRequest.of(page, size, Sort.by(orderBy));
            Page<Deputado> deputados = service.findAll(defaultPageable);
            Page<DeputadoDto> deputadoDtoPage = deputados.map(mapper::toDto);
            return ResponseEntity.ok(deputadoDtoPage);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Deputado>> getDeputadoById(@PathVariable Integer id) {
        Optional<Deputado> deputado = service.findById(id);
        return ResponseEntity.ok(deputado);
    }

//    @Hidden
    @PostMapping
    public ResponseEntity<Deputado> saveDeputado(@RequestBody Deputado deputado) {
        Deputado savedDeputado = service.save(deputado);
        return ResponseEntity.ok(savedDeputado);
    }

    //    @Hidden
    @PatchMapping("/{id}")
    public ResponseEntity<DeputadoDto> update(@PathVariable Integer id, @RequestBody DeputadoDto dto) {
        Optional<Deputado> updatedDeputado = service.update(id, dto);
        return updatedDeputado.map(value -> ResponseEntity.ok(mapper.toDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


//    @Hidden
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeputado(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}