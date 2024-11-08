package br.net.licks.db.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.net.licks.db.model.Sessao;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Integer> {
	@Query("SELECT s FROM Sessao s WHERE s.pauta.id = :pautaId ORDER BY s.fimVotacao DESC LIMIT 1")
	Optional<Sessao> findLatestSessionByPauta(@Param("pautaId") Integer pautaId);
}
