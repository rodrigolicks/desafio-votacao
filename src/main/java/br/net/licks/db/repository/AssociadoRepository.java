package br.net.licks.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.net.licks.db.model.Associado;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Integer>{}
