package com.org.recipe.service;

import java.util.List;

import com.org.recipe.model.Recipe;

/**
 * This class is having all the service methods,such as to
 * create,update,retrieve,delete data from Recipe table
 * 
 * @author smita
 *
 */

public interface RecipeService {

	/**
	 * This method is responsible retrieving all the Recipe information from DB.
	 * 
	 * @param no parameter
	 * @return List<Recipe>
	 */
	public List<Recipe> getAllRecipe();

	/**
	 * This method is to retrieve all the Recipe.
	 * 
	 * @param no parameter
	 * @return Recipe
	 */
	public Recipe getRecipeById(int id);

	/**
	 * This method is responsible for creating new Recipe.
	 * 
	 * @param id
	 */
	public Recipe save(Recipe recipe);

	/**
	 * This method is responsible for deleting a RecipeDTO.
	 * 
	 * @param id
	 */
	public void delete(int id);

	/**
	 * This method is responsible for updating a RecipeDTO.
	 * 
	 * @param recipe,id
	 */
	public Recipe update(Recipe recipe, int recipeId);

	/**
	 * This method is to find recipe by type
	 * 
	 * @param recipeType
	 * @return List<Recipe>
	 */
	public List<Recipe> getRecipesByType(String recipeType);

}
