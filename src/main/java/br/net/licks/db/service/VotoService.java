package br.net.licks.db.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.net.licks.db.dto.VotoRequest;
import br.net.licks.db.exception.BusinessLogicException;
import br.net.licks.db.model.Associado;
import br.net.licks.db.model.Sessao;
import br.net.licks.db.model.Voto;
import br.net.licks.db.repository.AssociadoRepository;
import br.net.licks.db.repository.VotoRepository;
import lombok.Data;

@Service
@Data
public class VotoService {

	private final SessaoService sessaoService;
	private final AssociadoRepository associadoRepository;
	private final VotoRepository votoRepository;
	
	public Voto vote(VotoRequest voto) {

		Integer pautaId = voto.getPautaId();
		Integer associadoId = voto.getAssociadoId();

		Optional<Associado> associado = getAssociadoRepository().findById(associadoId);
		if (!associado.isPresent()) {
			throw new BusinessLogicException("associadoId", "Associado inválido ou inexistente");
		}

		Optional<Sessao> sessao = getSessaoService().getLatestSessionByPauta(pautaId);
		if (!sessao.isPresent() || !sessao.get().isAberta()) {
			throw new BusinessLogicException("pautaId", "Pauta não possui sessão aberta");
		}
	
		if (isAssociateAlreadyVotedInSession(sessao.get().getId(), associadoId)) {
			throw new BusinessLogicException("sessaoId", "Já existe um voto do associado nesta sessão");
		};
		
		switch (voto.getVoto().toLowerCase()) {
			case "sim":
				sessao.get().incrementarSim();
				break;
			case "não":
			case "nao":
				sessao.get().incrementarNao();
				break;
		}
		
		Voto entity = new Voto();
		entity.setAssociado(associado.get());
		entity.setSessao(sessao.get());
		
		return votoRepository.save(entity);
	}
	
	public boolean isAssociateAlreadyVotedInSession(Integer sessaoId, Integer associadoId) {
		Optional<Voto> voto = getVotoRepository().findFirstVotoByAssociadoAndSessao(associadoId, sessaoId);
		return voto.isPresent();
	}
	
}
