package br.com.GS4.wetPolicies.core.repository;

import br.com.GS4.wetPolicies.core.model.entity.Bancada;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BancadaRepository extends JpaRepository<Bancada, Integer> {

    @Override
    Page<Bancada> findAll(Pageable pageable);

    List<Bancada> findByClassificacao(String classificacao);
}
