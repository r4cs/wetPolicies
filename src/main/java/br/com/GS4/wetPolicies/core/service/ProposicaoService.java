package br.com.GS4.wetPolicies.core.service;

import br.com.GS4.wetPolicies.core.controller.dadosAbertosAPI.CamaraClientApiNova;
import br.com.GS4.wetPolicies.core.model.entity.Proposicao;
import br.com.GS4.wetPolicies.core.repository.ProposicaoRepository;
import br.com.GS4.wetPolicies.core.service.strategy.PoliticaMarinhaStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProposicaoService {
    private final CamaraClientApiNova camaraClient;
    private final ProposicaoRepository proposicaoRepository;
    private final PoliticaMarinhaStrategy politicaMarinhaStrategy;

    @Autowired
    public ProposicaoService(ProposicaoRepository proposicaoRepository, PoliticaMarinhaStrategy politicaMarinhaStrategy, CamaraClientApiNova camaraClient) {
        this.proposicaoRepository = proposicaoRepository;
        this.politicaMarinhaStrategy = politicaMarinhaStrategy;
        this.camaraClient = camaraClient;
    }

    public List<Proposicao> fetchAndSaveProposicoes(Map<String, String> params) {
        List<Proposicao> proposicoes = camaraClient.getProposicoes(params);
        proposicaoRepository.saveAll(proposicoes);
        return proposicoes;
    }

    public List<Proposicao> fetchProposicoes(Map<String, String> params) {
        System.out.println("\n\n*** Params em fetchProposicoes: " + params);
        System.out.println("\n\ncamaraClient.getProposicoes(params): " + camaraClient.getProposicoes(params));
        System.out.println("\n\n");
        return camaraClient.getProposicoes(params);
    }


    public List<Proposicao> findAll() {
        List<Proposicao> proposicoes = proposicaoRepository.findAll();
        return politicaMarinhaStrategy.filtrarProposicoesPoliticaMarinha(proposicoes);
    }

    public Optional<Proposicao> findById(Integer id) {
        return proposicaoRepository.findById(id);
    }

    public void save(Proposicao proposicao) {
        proposicaoRepository.save(proposicao);
    }

//    public Proposicao save(Proposicao proposicao) {
//        return proposicaoRepository.save(proposicao);
//    }

    public void deleteById(Integer id) {
        proposicaoRepository.deleteById(id);
    }
}
