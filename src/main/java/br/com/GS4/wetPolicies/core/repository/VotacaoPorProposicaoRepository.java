package br.com.GS4.wetPolicies.core.repository;

import br.com.GS4.wetPolicies.core.model.entity.Votacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotacaoPorProposicaoRepository extends JpaRepository<Votacao, Integer> {
    @Override
    Page<Votacao> findAll(Pageable pageable);
}
