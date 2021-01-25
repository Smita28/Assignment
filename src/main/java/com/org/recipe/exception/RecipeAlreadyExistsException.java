package com.org.recipe.exception;

/**
 * RecipeNotFoundException custom Exception
 * @author smita
 *
 */
public class RecipeAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	public RecipeAlreadyExistsException() {
		super();
	  }

	public RecipeAlreadyExistsException(String name) {
	    super("Recipe " + name +" already exists.");
	  }
}
