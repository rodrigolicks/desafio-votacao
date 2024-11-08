package br.net.licks.db.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.net.licks.db.dto.SessaoRequest;
import br.net.licks.db.model.Sessao;
import br.net.licks.db.repository.SessaoRepository;
import br.net.licks.db.service.SessaoService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Getter
public class SessaoController {
	
	private final SessaoRepository sessaoRepository;
	private final SessaoService sessaoService;
	
	@GetMapping("/sessao")
	public List<Sessao> getSessoes() {
		return getSessaoRepository().findAll();
	}
	
	@PostMapping("/sessao")
	public Sessao submitSessao(@RequestBody SessaoRequest request) {
		return getSessaoService().save(request);
	}
	
}
