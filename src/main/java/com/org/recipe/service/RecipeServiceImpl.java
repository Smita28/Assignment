package com.org.recipe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.recipe.exception.InvalidInputException;
import com.org.recipe.exception.RecipeAlreadyExistsException;
import com.org.recipe.exception.RecipeNotFoundException;
import com.org.recipe.model.Recipe;
import com.org.recipe.repository.RecipeRepository;
/**
 * This class is implementation class of recipe service.
 * @author smita
 *
 */
@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	RecipeRepository recipeRepository;

	/**
	 * This method is to get all the recipes.
	 * 
	 * @return  List<Recipe>
	 * @param 
	 */
	@Override
	public List<Recipe> getAllRecipe() {
		return (List<Recipe>)recipeRepository.findAll();
	}

	/**
	 * This method to get recipe by id
	 * 
	 * @return  List<Recipe>
	 * @param id
	 */
	@Override
	public Recipe getRecipeById(int id) {
		return recipeRepository.findById(id)
			      .orElseThrow(() -> new RecipeNotFoundException(id));
	}
	/**
     *This method is to save recipe	 * 
	 * @return  Recipe
	 * @param recipe
	 */
	@Override
	public Recipe save(Recipe recipe) {
		
		String recipeName = recipe.getName();
		if(recipe.getDescription().isEmpty() || recipe.getName().isEmpty()|| recipe.getType().isEmpty()) {
			throw new InvalidInputException("Recipe Description, Name and type should not be empty .");
		}
		if(null != recipeRepository.findByName(recipeName.strip())) {
			throw new RecipeAlreadyExistsException(recipeName);
		}
		return recipeRepository.save(recipe);
	}

	/**
     *This method is to delete recipe	 * 
	 * @return  
	 * @param id
	 */
	@Override
	public void delete(int id) {
		if(!recipeRepository.findById(id).isPresent()) {
			throw new RecipeNotFoundException(id);
		}
		recipeRepository.deleteById(id);
	}
	/**
     *This method is to update recipe	 * 
	 * @return  Recipe
	 * @param recipe,recipeId
	 */
	@Override
	public Recipe update(Recipe recipe, int recipeId) {
		String recipeName = recipe.getName();
		recipe.setId(recipeId);
		if(!recipeRepository.findById(recipeId).isPresent()) {
			throw new RecipeNotFoundException(recipeId);
		}
		return recipeRepository.save(recipe);
	}

	/**
     *This method is to get recipe by type	 * 
	 * @return  List<Recipe>
	 * @param recipeType
	 */
	public List<Recipe> getRecipesByType(String recipeType) {
		List<Recipe> recipies;
		if(!"".equalsIgnoreCase(recipeType) && (recipeType.equalsIgnoreCase("veg") || recipeType.equalsIgnoreCase("non-veg"))) {
			recipies = recipeRepository.findAllByType(recipeType);
			if(recipies.isEmpty()) {
				throw new RecipeNotFoundException();
			}
		}else {
			throw new InvalidInputException("Please provide correct data.");
		}
		return recipies;
	}
	
}
