package br.com.GS4.wetPolicies.core.service;

import br.com.GS4.wetPolicies.core.model.entity.Proposicao;
import br.com.GS4.wetPolicies.core.service.dadosAbertosAPI.CamaraDeputadoService;
import br.com.GS4.wetPolicies.core.model.DTO.DeputadoDto;
import br.com.GS4.wetPolicies.core.model.entity.Deputado;
import br.com.GS4.wetPolicies.core.repository.DeputadoRepository;
import br.com.GS4.wetPolicies.core.service.strategy.PoliticaMarinhaStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeputadoService {
    private final DeputadoRepository repository;

    @Autowired
    public DeputadoService(DeputadoRepository repository) {
        this.repository = repository;
    }

    public Page<Deputado> findAll(Pageable page) {
        return repository.findAll(page);
//        return politicaMarinhaStrategy.filtrarDeputadosPoliticaMarinha(deputados.toList());
    }

    public List<Deputado> findAll() {
        return repository.findAll();
    }


    public Optional<Deputado> findById(Integer id) {
        return repository.findById(id);
    }

    public Deputado save(Deputado deputado) {
        return repository.save(deputado);
    }

    public Optional<Deputado> update(Integer id, DeputadoDto deputadoDto) {
        return repository.findById(id).map(existingDeputado -> {
            if (deputadoDto.nome() != null) {
                existingDeputado.setNome(deputadoDto.nome());
            }
            if (deputadoDto.uf() != null) {
                existingDeputado.setUf(deputadoDto.uf());
            }
            if (deputadoDto.classificacao() != null) {
                existingDeputado.setClassificacao(deputadoDto.classificacao());
            }
            if (deputadoDto.partido() != null) {
                existingDeputado.setPartido(deputadoDto.partido());
            }
            if (deputadoDto.votacao() != null) {
                existingDeputado.setVotacao(deputadoDto.votacao());
            }
            return repository.save(existingDeputado);
        });
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public List<Deputado> findByClassificacao(String classificacao) {
        return repository.findByClassificacao(classificacao);
    }
}
