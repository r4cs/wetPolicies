package br.com.GS4.wetPolicies.core.repository;

import br.com.GS4.wetPolicies.core.model.entity.Proposicao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProposicaoRepository extends JpaRepository<Proposicao, Integer> {
    @Override
    Page<Proposicao> findAll(Pageable pageable);

    @Query("SELECT p FROM Proposicao p LEFT JOIN FETCH p.votacoes WHERE p.id = :id")
    Proposicao findProposicaoWithVotacoesById(@Param("id") Long id);

}
