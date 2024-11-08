package br.net.licks.db.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessLogicException extends RuntimeException {
	private static final long serialVersionUID = -2945884352691290821L;
	private String field;
	private String message;
	
	public BusinessLogicException(String field, String message) {
		this.field = field;
		this.message = message;
	}
}
