package br.com.GS4.wetPolicies.core.service;

import br.com.GS4.wetPolicies.core.model.entity.OrientacaoBancada;
import br.com.GS4.wetPolicies.core.model.entity.Proposicao;
import br.com.GS4.wetPolicies.core.repository.OrientacaoBancadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrientacaoBancadaService {
    private final OrientacaoBancadaRepository repository;

    @Autowired
    public OrientacaoBancadaService(OrientacaoBancadaRepository repository) {
        this.repository = repository;
    }

    public Page<OrientacaoBancada> findAll(Pageable page) {
        return repository.findAll(page);
//        return politicaMarinhaStrategy.filtrarOrientacaoBancadasPoliticaMarinha(bancadas.toList());
    }

    public List<OrientacaoBancada> findAll() {
        return repository.findAll();
    }


    public Optional<OrientacaoBancada> findById(Integer id) {
        return repository.findById(id);
    }

    public OrientacaoBancada save(OrientacaoBancada bancada) {
        return repository.save(bancada);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

}
