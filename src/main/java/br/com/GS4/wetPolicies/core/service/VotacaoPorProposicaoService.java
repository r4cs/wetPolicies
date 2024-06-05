package br.com.GS4.wetPolicies.core.service;

import br.com.GS4.wetPolicies.core.model.DTO.VotacaoDto;
import br.com.GS4.wetPolicies.core.model.entity.VotacaoPorProposicao;
import br.com.GS4.wetPolicies.core.repository.VotacaoPorProposicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VotacaoPorProposicaoService {
    private final VotacaoPorProposicaoRepository votacaoRepository;

    @Autowired
    public VotacaoPorProposicaoService(VotacaoPorProposicaoRepository votacaoRepository) {
        this.votacaoRepository = votacaoRepository;
    }

    public List<VotacaoDto> findAll() {
        return votacaoRepository.findAll().stream()
                .map(VotacaoDto::new)
                .collect(Collectors.toList());
    }

    public Optional<VotacaoPorProposicao> findById(String id) {
        return votacaoRepository.findById(id);
    }

    public void save(VotacaoPorProposicao votacao) {
        votacaoRepository.save(votacao);
    }

    public void deleteById(String id) {
        votacaoRepository.deleteById(id);
    }
}
