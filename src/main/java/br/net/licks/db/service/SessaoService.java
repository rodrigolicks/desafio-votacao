package br.net.licks.db.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.net.licks.db.dto.SessaoRequest;
import br.net.licks.db.exception.BusinessLogicException;
import br.net.licks.db.model.Pauta;
import br.net.licks.db.model.Sessao;
import br.net.licks.db.repository.PautaRepository;
import br.net.licks.db.repository.SessaoRepository;
import jakarta.validation.Valid;
import lombok.Data;

@Service
@Data
public class SessaoService {
	
	private final PautaRepository pautaRepository;
	private final SessaoRepository sessaoRepository;
	
	public Sessao save(@Valid @RequestBody SessaoRequest request) {
		
		Optional<Pauta> pauta = getPautaRepository().findById(request.getPautaId());

		if (!pauta.isPresent()) {
			throw new BusinessLogicException("pautaId", "Pauta inválida ou inexistente");
		}

		Optional<Sessao> ultimaSessao = getLatestSessionByPauta(pauta.get().getId());		
		if (ultimaSessao.isPresent() && ultimaSessao.get().isAberta()) {
			throw new BusinessLogicException("pautaId", "Já existe uma sessão de votação aberta para a pauta");
		}
		
		Sessao entity = new Sessao();
		entity.setDuracaoVotacao(request.getDuracaoVotacao());
		entity.setPauta(pauta.get());		
		entity.setAberta(true);
		return getSessaoRepository().save(entity);
		
	}
	
	public Optional<Sessao> getLatestSessionByPauta(Integer pautaId) {
		return getSessaoRepository().findLatestSessionByPauta(pautaId);
	}

}
