package br.com.GS4.wetPolicies.core.service;

import br.com.GS4.wetPolicies.core.service.dadosAbertosAPI.CamaraDeputadoService;
import br.com.GS4.wetPolicies.core.model.DTO.DeputadoDto;
import br.com.GS4.wetPolicies.core.model.entity.Deputado;
import br.com.GS4.wetPolicies.core.repository.DeputadoRepository;
import br.com.GS4.wetPolicies.core.service.strategy.PoliticaMarinhaStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeputadoService {
    private final DeputadoRepository deputadoRepository;
    private final PoliticaMarinhaStrategy politicaMarinhaStrategy;

    @Autowired
    public DeputadoService(DeputadoRepository deputadoRepository, PoliticaMarinhaStrategy politicaMarinhaStrategy) {
        this.deputadoRepository = deputadoRepository;
        this.politicaMarinhaStrategy = politicaMarinhaStrategy;
    }

    public List<DeputadoDto> findAll() {
        List<DeputadoDto> deputados = deputadoRepository.findAll().stream()
                .map(DeputadoDto::new)
                .collect(Collectors.toList());
        return politicaMarinhaStrategy.filtrarDeputadosPoliticaMarinha(deputados);
    }

    public Optional<Deputado> findById(Integer id) {
        return deputadoRepository.findById(id);
    }

    public Deputado save(Deputado deputado) {
        return deputadoRepository.save(deputado);
    }

    public void deleteById(Integer id) {
        deputadoRepository.deleteById(id);
    }

    public List<Deputado> findByClassificacao(String classificacao) {
        return deputadoRepository.findByClassificacao(classificacao);
    }
}
