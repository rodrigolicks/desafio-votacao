package br.net.licks.db.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.net.licks.db.dto.CpfRequest;
import br.net.licks.db.service.CpfService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Getter
public class CpfController {
	
	private final CpfService cpfService;
	
	@PostMapping("/cpf")
	public ResponseEntity<Map<String, String>> isCpfEnabledForVoting(@Valid @RequestBody CpfRequest cpf, BindingResult errors) {
		if (errors.hasErrors()) {
			Map<String, String> responseMap = new HashMap<String, String>();
			responseMap.put("status", "UNABLE_TO_VOTE");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
		}
		
		return getCpfService().checkCpfForVoting();		
	}
}
