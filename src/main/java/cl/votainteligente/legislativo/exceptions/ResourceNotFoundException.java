package cl.votainteligente.legislativo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = -1047678371267298618L;
}
