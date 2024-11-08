package br.net.licks.db.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.net.licks.db.model.Associado;
import br.net.licks.db.repository.AssociadoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class AssociadoController {
	
	private final AssociadoRepository associadoRepository;
	
	@GetMapping("/associado")
	public List<Associado> getAssociados() {
		return associadoRepository.findAll();
	}
	
	@PostMapping("/associado")
	public Associado submitAssociado(@Valid @RequestBody Associado request) {
		return associadoRepository.save(request);
	}
	
}
