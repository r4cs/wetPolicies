package br.com.GS4.wetPolicies.core.service.strategy;

import br.com.GS4.wetPolicies.core.model.entity.OrientacaoBancada;
import br.com.GS4.wetPolicies.core.repository.OrientacaoBancadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrientacaoBancadaService {
    private final OrientacaoBancadaRepository orientacaoBancadaRepository;

    @Autowired
    public OrientacaoBancadaService(OrientacaoBancadaRepository orientacaoBancadaRepository) {
        this.orientacaoBancadaRepository = orientacaoBancadaRepository;
    }

    public Page<OrientacaoBancada> findAll(Pageable page) {
        return orientacaoBancadaRepository.findAll(page);
//        return politicaMarinhaStrategy.filtrarOrientacaoBancadasPoliticaMarinha(bancadas.toList());
    }

    public Optional<OrientacaoBancada> findById(Integer id) {
        return orientacaoBancadaRepository.findById(id);
    }

    public OrientacaoBancada save(OrientacaoBancada bancada) {
        return orientacaoBancadaRepository.save(bancada);
    }

    public void deleteById(Integer id) {
        orientacaoBancadaRepository.deleteById(id);
    }

}
