package br.net.licks.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.net.licks.db.model.Pauta;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Integer>{}
