package br.net.licks.db.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Pattern.Flag;
import lombok.Data;

@Data
public class VotoRequest {
	
	@NotNull
	@Min(1)
	private Integer pautaId;
	
	@NotNull
	@Min(1)
	private Integer associadoId;
	
    @Pattern(regexp = "SIM|NÃO", flags = Flag.CASE_INSENSITIVE, message = "Voto deve ser SIM ou NÃO")
	private String voto;

}
