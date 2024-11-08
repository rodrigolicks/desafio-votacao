package br.net.licks.db.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class SessaoRequest {
	
	@Min(1)
	private Integer pautaId;
	private LocalDateTime inicioVotacao;
	@Min(1)
	private Integer duracaoVotacao;

}
