package br.net.licks.db.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.net.licks.db.dto.VotoRequest;
import br.net.licks.db.model.Voto;
import br.net.licks.db.service.VotoService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Getter
public class VotoController {
	
	private final VotoService votoService;
	
	@PostMapping("/voto")
	public @Valid Voto submitVoto(@Valid @RequestBody VotoRequest request) {
		return getVotoService().vote(request);
	}
	
}
