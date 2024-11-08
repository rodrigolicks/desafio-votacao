package br.net.licks.db.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.net.licks.db.model.Voto;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Integer>{
	@Query("SELECT v FROM Voto v WHERE v.sessao.id = :sessaoId AND v.associado.id = :associadoId "
			+ "ORDER BY v.sessao.id DESC LIMIT 1")
	Optional<Voto> findFirstVotoByAssociadoAndSessao(@Param("associadoId") Integer associadoId,
			@Param("sessaoId") Integer sessaoId);	
}
