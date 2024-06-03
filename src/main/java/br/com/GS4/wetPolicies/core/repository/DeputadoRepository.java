package br.com.GS4.wetPolicies.core.repository;

import br.com.GS4.wetPolicies.core.model.entity.Deputado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeputadoRepository extends JpaRepository<Deputado, Integer> {

    @Override
    Page<Deputado> findAll(Pageable pageable);

    List<Deputado> findByClassificacao(String classificacao);
}
