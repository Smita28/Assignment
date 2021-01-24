package com.org.recipe.exception;

/**
 * RecipeNotFoundException custom Exception
 * @author smita
 *
 */
public class RecipeAlreadExistsException extends RuntimeException {

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public RecipeAlreadExistsException() {
		super();
	  }

	public RecipeAlreadExistsException(String name) {
	    super("Recipe " + name +" already exists.");
	  }
}
