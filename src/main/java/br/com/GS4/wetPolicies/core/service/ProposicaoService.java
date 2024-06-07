package br.com.GS4.wetPolicies.core.service;

import br.com.GS4.wetPolicies.core.controller.dadosAbertosAPI.CamaraClientApiNova;
import br.com.GS4.wetPolicies.core.model.entity.Proposicao;
import br.com.GS4.wetPolicies.core.repository.ProposicaoRepository;
import br.com.GS4.wetPolicies.core.service.strategy.PoliticaMarinhaStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProposicaoService {
    private final ProposicaoRepository repository;
    private final PoliticaMarinhaStrategy politicaMarinhaStrategy;

    @Autowired
    public ProposicaoService(ProposicaoRepository repository, PoliticaMarinhaStrategy politicaMarinhaStrategy) {
        this.repository = repository;
        this.politicaMarinhaStrategy = politicaMarinhaStrategy;
    }


    public Page<Proposicao> findAll(Pageable page) {
        return repository.findAll(page);
    }

    public List<Proposicao> findAll() {
        return repository.findAll();
    }


    public Optional<Proposicao> findById(Integer id) {
        return repository.findById(id);
    }

    public Proposicao save(Proposicao proposicao) {
        return repository.save(proposicao);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

}
