package com.org.recipe.exception;

/**
 * RecipeNotFoundException custom Exception
 * @author beher
 *
 */
public class RecipeNotFoundException extends RuntimeException {

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public RecipeNotFoundException() {
	  }

	public RecipeNotFoundException(int id) {
	    super("Could not find recipe " + id);
	  }
}
