package br.com.GS4.wetPolicies.core.service.dadosAbertosAPI;

import br.com.GS4.wetPolicies.core.controller.dadosAbertosAPI.CamaraClientApiAntiga;
import br.com.GS4.wetPolicies.core.model.entity.Deputado;
import br.com.GS4.wetPolicies.core.model.entity.VotacaoPorProposicao;
import br.com.GS4.wetPolicies.core.repository.VotacaoPorProposicaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CamaraVotacaoService {
    private static final Logger logger = LoggerFactory.getLogger(CamaraVotacaoService.class);
    private final CamaraClientApiAntiga camaraClient;
    private final VotacaoPorProposicaoRepository votacaoRepository;


    public CamaraVotacaoService(CamaraClientApiAntiga camaraClient, VotacaoPorProposicaoRepository votacaoRepository) {
        this.camaraClient = camaraClient;
        this.votacaoRepository = votacaoRepository;
    }

    public void fetchAndSaveVotacoes(Map<String, String> params) {
        try {
            logger.info("*** *** Fetching votacoes with params: {}", params);
            List<VotacaoPorProposicao> votacoes = camaraClient.getVotacaoPorProposicao(params);
            votacaoRepository.saveAll(votacoes);
            logger.info("*** *** Saved {} votacoes to repository", votacoes.size());
        } catch (Exception e) {
            logger.error("*** *** Error fetching and saving votacoes: {}", e.getMessage());
        }
    }

    public List<VotacaoPorProposicao> fetchVotacao(Map<String, String> params) {
        logger.info("*** *** Fetching votacoes with params: {}", params);
        return camaraClient.getVotacaoPorProposicao(params);
    }

//    public VotacaoPorProposicao fetchVotacaoPorId(Integer id) {
//        logger.info("*** *** Fetching votacao by id: {}", id);
//        return camaraClient.getVotacaoPorProposicaoById(id);
//    }


}
