package br.com.me;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Registro não encontrado")
public class DataNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2560810278277887585L;

	public DataNotFoundException() {
		super();
	}

	public DataNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DataNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataNotFoundException(String message) {
		super(message);
	}

	public DataNotFoundException(Throwable cause) {
		super(cause);
	}
	
	
	
	
	

}
 