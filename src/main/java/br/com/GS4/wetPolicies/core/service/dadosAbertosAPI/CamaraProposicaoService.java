package br.com.GS4.wetPolicies.core.service.dadosAbertosAPI;

import br.com.GS4.wetPolicies.core.controller.dadosAbertosAPI.CamaraClientApiNova;
import br.com.GS4.wetPolicies.core.model.entity.Proposicao;
import br.com.GS4.wetPolicies.core.repository.ProposicaoRepository;
import br.com.GS4.wetPolicies.core.service.mappers.ProposicaoMapperImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CamaraProposicaoService {
    private static final Logger logger = LoggerFactory.getLogger(CamaraProposicaoService.class);

    private final CamaraClientApiNova camaraClient;

    @Autowired
    public CamaraProposicaoService(CamaraClientApiNova camaraClient) {
        this.camaraClient = camaraClient;
    }

//    public List<Proposicao> getProposicoes(Map<String, String> params) {
//        return camaraClient.getProposicoes(params);
//    }

//    public List<ProposicaoDto> getProposicoesById(Long id) {
//        return camaraClient.getProposicoes(id);
//    }

}
