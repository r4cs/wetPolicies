package br.com.GS4.wetPolicies.core.service;

import br.com.GS4.wetPolicies.core.model.DTO.BancadaDto;
import br.com.GS4.wetPolicies.core.model.entity.Bancada;
import br.com.GS4.wetPolicies.core.model.entity.Deputado;
import br.com.GS4.wetPolicies.core.model.entity.Proposicao;
import br.com.GS4.wetPolicies.core.repository.BancadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BancadaService {
    private final BancadaRepository repository;

    @Autowired
    public BancadaService(BancadaRepository repository) {
        this.repository = repository;
    }

    public Page<Bancada> findAll(Pageable page) {
        return repository.findAll(page);
    }

    public List<Bancada> findAll() {
        return repository.findAll();
    }


    public Optional<Bancada> findById(Integer id) {
        return repository.findById(id);
    }

    public Bancada save(Bancada deputado) {
        return repository.save(deputado);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public List<Bancada> findByClassificacao(String classificacao) {
        return repository.findByClassificacao(classificacao);
    }

    public Optional<Bancada> findByNome(String sigla) {
        return repository.findByNome(sigla);
    }

    public Optional<Bancada> update(Integer id, BancadaDto bancadaDto) {
        return repository.findById(id).map(existingBancada -> {
            if (bancadaDto.nome() != null) {
                existingBancada.setNome(bancadaDto.nome());
            }
            if (bancadaDto.orientacoesBancadas() != null) {
                existingBancada.setOrientacoesBancadas(bancadaDto.orientacoesBancadas());
            }
            if (bancadaDto.classificacao() != null) {
                existingBancada.setClassificacao(bancadaDto.classificacao());
            }
            return repository.save(existingBancada);
        });
    }

}
