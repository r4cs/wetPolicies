package br.com.GS4.wetPolicies.core.service;

import br.com.GS4.wetPolicies.core.controller.dadosAbertosAPI.CamaraClient;
import br.com.GS4.wetPolicies.core.model.DTO.VotacaoDto;
import br.com.GS4.wetPolicies.core.model.entity.Votacao;
import br.com.GS4.wetPolicies.core.repository.VotacaoRepository;
import br.com.GS4.wetPolicies.core.service.strategy.PoliticaMarinhaStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VotacaoService {
    private final CamaraClient camaraClient;
    private final VotacaoRepository votacaoRepository;
    private final PoliticaMarinhaStrategy politicaMarinhaStrategy;

    @Autowired
    public VotacaoService(VotacaoRepository votacaoRepository, PoliticaMarinhaStrategy politicaMarinhaStrategy, CamaraClient camaraClient) {
        this.votacaoRepository = votacaoRepository;
        this.politicaMarinhaStrategy = politicaMarinhaStrategy;
        this.camaraClient = camaraClient;
    }

    public void fetchAndSaveVotacoes(Map<String, String> params) {
        List<Votacao> votacoes = camaraClient.getVotacoes(params);
        votacaoRepository.saveAll(votacoes);
    }

    public List<VotacaoDto> findAll() {
        List<VotacaoDto> votacoes = votacaoRepository.findAll().stream()
                .map(VotacaoDto::new)
                .collect(Collectors.toList());
        return politicaMarinhaStrategy.filtrarVotacoesPoliticaMarinha(votacoes);
    }

    public Optional<Votacao> findById(String id) {
        return votacaoRepository.findById(id);
    }

    public Votacao save(Votacao votacao) {
        return votacaoRepository.save(votacao);
    }

    public void deleteById(String id) {
        votacaoRepository.deleteById(id);
    }
}
