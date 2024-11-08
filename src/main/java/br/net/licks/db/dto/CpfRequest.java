package br.net.licks.db.dto;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CpfRequest {
	
	@NotBlank
	@CPF
	private String cpf;

}
