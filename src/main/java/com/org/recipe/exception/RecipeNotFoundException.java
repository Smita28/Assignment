package com.org.recipe.exception;

/**
 * RecipeNotFoundException custom Exception
 * @author smita
 *
 */
public class RecipeNotFoundException extends RuntimeException {

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public RecipeNotFoundException() {
		super();
	  }

	public RecipeNotFoundException(int id) {
	    super("Could not find recipe " + id);
	  }
}
