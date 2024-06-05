package br.com.GS4.wetPolicies.core.repository;

import br.com.GS4.wetPolicies.core.model.entity.Proposicao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProposicaoRepository extends JpaRepository<Proposicao, Integer> {
    @Override
    Page<Proposicao> findAll(Pageable pageable);
}
