package br.com.GS4.wetPolicies.core.repository;

import br.com.GS4.wetPolicies.core.model.entity.Proposicao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProposicaoRepository extends JpaRepository<Proposicao, Integer> {
}
