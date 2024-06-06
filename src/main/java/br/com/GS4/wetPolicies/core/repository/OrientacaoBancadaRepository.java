package br.com.GS4.wetPolicies.core.repository;

import br.com.GS4.wetPolicies.core.model.entity.Bancada;
import br.com.GS4.wetPolicies.core.model.entity.OrientacaoBancada;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrientacaoBancadaRepository extends JpaRepository<OrientacaoBancada, Integer> {

    @Override
    Page<OrientacaoBancada> findAll(Pageable pageable);

}
