package br.com.GS4.wetPolicies.core.service.dadosAbertosAPI;

import br.com.GS4.wetPolicies.core.controller.dadosAbertosAPI.CamaraClient;
import br.com.GS4.wetPolicies.core.model.entity.Deputado;
import br.com.GS4.wetPolicies.core.repository.DeputadoRepository;
import br.com.GS4.wetPolicies.core.service.strategy.PoliticaMarinhaStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.List;
import java.util.Map;

@Service
public class CamaraDeputadoService {
    private static final Logger logger = LoggerFactory.getLogger(CamaraDeputadoService.class);

    private final CamaraClient camaraClient;
    private final DeputadoRepository deputadoRepository;

    @Autowired
    public CamaraDeputadoService(DeputadoRepository deputadoRepository, CamaraClient camaraClient) {
        this.deputadoRepository = deputadoRepository;
        this.camaraClient = camaraClient;
    }

    public void fetchAndSaveDeputados(Map<String, String> params) {
        try {
            logger.info("*** *** Fetching deputados with params: {}", params);
            List<Deputado> deputados = camaraClient.getDeputados(params);
            deputadoRepository.saveAll(deputados);
            logger.info("*** *** Saved {} deputados to repository", deputados.size());
        } catch (Exception e) {
            logger.error("*** *** Error fetching and saving deputados: {}", e.getMessage());
        }
    }

    public List<Deputado> fetchDeputados(Map<String, String> params) {
        logger.info("*** *** Fetching deputados with params: {}", params);
        return camaraClient.getDeputados(params);
    }
    public Deputado fetchDeputadoById(Integer id) {
        logger.info("*** *** Fetching deputado by id: {}", id);
        return camaraClient.getDeputadoById(id);
    }
}
