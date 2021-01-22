package com.org.recipe.service;

import java.util.List;

import com.org.recipe.model.Recipe;
/**
 * This class is having all the service methods,such as to create,update,retrieve,delete data from RecipeDTO table
 * @author smita
 *
 */

public interface RecipeService {

/**
 * This method is responsible retrieving all the RecipeDTO information from DB.
 * @param no parameter
 * @return List<RecipeDTO>
 */
	public List<Recipe> getAllRecipe();
	
	/**
	 * This method is to retrieve all the RecipeDTO.
	 * @param no parameter
	 * @return List<RecipeDTO>
	 */
	public Recipe getRecipeById(int id);

	/**
	 * This method is responsible for creating new RecipeDTO.
	 * @param id
	 * @return RecipeDTO
	 */
	public void saveOrUpdate(Recipe recipe);

	/**
	 * This method is responsible for deleting a RecipeDTO.
	 * @param id
	 * @return void
	 */
	public void delete(int id);
	/**
	 * This method is responsible for updating a RecipeDTO.
	 * @param no RecipeDTO,id
	 * @return void
	 */
	public void update(Recipe recipe, int recipeId);
	
	
}
