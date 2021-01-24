package com.org.recipe.exception;

/**
 * This class is to handle invalid input exception.
 * @author smita
 *
 */
public class InvalidInputException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public InvalidInputException() {
		super();
	}
	
	public InvalidInputException(final String message) {
		super(message);
	}

}
