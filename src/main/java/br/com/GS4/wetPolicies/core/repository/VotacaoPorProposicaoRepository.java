package br.com.GS4.wetPolicies.core.repository;

import br.com.GS4.wetPolicies.core.model.entity.VotacaoPorProposicao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotacaoPorProposicaoRepository extends JpaRepository<VotacaoPorProposicao, String> {
    @Override
    Page<VotacaoPorProposicao> findAll(Pageable pageable);
}
