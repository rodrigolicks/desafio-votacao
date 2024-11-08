package br.net.licks.db.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.net.licks.db.model.Pauta;
import br.net.licks.db.repository.PautaRepository;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Getter
public class PautaController {
	
	private final PautaRepository pautaRepository;
	
	@GetMapping("/pauta")
	public List<Pauta> getPautas() {
		return getPautaRepository().findAll();
	}
	
	@PostMapping("/pauta")
	public Pauta submitPauta(@Valid @RequestBody Pauta pauta) {
		return getPautaRepository().save(pauta);
	}
	
}
