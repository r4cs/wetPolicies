package br.com.GS4.wetPolicies.core.service;

import br.com.GS4.wetPolicies.core.model.entity.Bancada;
import br.com.GS4.wetPolicies.core.model.entity.Deputado;
import br.com.GS4.wetPolicies.core.repository.BancadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BancadaService {
    private final BancadaRepository bancadaRepository;

    @Autowired
    public BancadaService(BancadaRepository bancadaRepository) {
        this.bancadaRepository = bancadaRepository;
    }

    public Page<Bancada> findAll(Pageable page) {
        return bancadaRepository.findAll(page);
//        return politicaMarinhaStrategy.filtrarBancadasPoliticaMarinha(deputados.toList());
    }

    public Optional<Bancada> findById(Integer id) {
        return bancadaRepository.findById(id);
    }

    public Bancada save(Bancada deputado) {
        return bancadaRepository.save(deputado);
    }

    public void deleteById(Integer id) {
        bancadaRepository.deleteById(id);
    }

    public List<Bancada> findByClassificacao(String classificacao) {
        return bancadaRepository.findByClassificacao(classificacao);
    }

}
