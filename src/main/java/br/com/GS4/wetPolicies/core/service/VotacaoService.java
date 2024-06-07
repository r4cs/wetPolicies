package br.com.GS4.wetPolicies.core.service;

import br.com.GS4.wetPolicies.core.model.DTO.VotacaoDto;
import br.com.GS4.wetPolicies.core.model.entity.Proposicao;
import br.com.GS4.wetPolicies.core.model.entity.Votacao;
import br.com.GS4.wetPolicies.core.repository.VotacaoPorProposicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VotacaoService {
    private final VotacaoPorProposicaoRepository repository;

    @Autowired
    public VotacaoService(VotacaoPorProposicaoRepository repository) {
        this.repository = repository;
    }


    public Page<Votacao> findAll(Pageable page) {
        return repository.findAll(page);
    }

    public List<Votacao> findAll() {
        return repository.findAll();
    }

    public Optional<Votacao> findById(Integer id) {
        return repository.findById(id);
    }

    public Votacao save(Votacao votacao) {
        return repository.save(votacao);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
