package com.org.recipe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.recipe.exception.InvalidInputException;
import com.org.recipe.exception.RecipeAlreadExistsException;
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

	@Override
	public List<Recipe> getAllRecipe() {
		return (List<Recipe>)recipeRepository.findAll();
	}

	/**
	 *This method to get recipe by id
	 */
	@Override
	public Recipe getRecipeById(int id) {
		return recipeRepository.findById(id)
			      .orElseThrow(() -> new RecipeNotFoundException(id));
	}

	/**
	 *This method is to save recipe
	 */
	@Override
	public Recipe save(Recipe recipe) {
		
		String recipeName = recipe.getName();
		if(null != recipeRepository.findByName(recipeName.strip())) {
			throw new RecipeAlreadExistsException(recipeName);
		}
		return recipeRepository.save(recipe);
	}

	
	/**
	 *
	 */
	@Override
	public void delete(int id) {
		if(!recipeRepository.findById(id).isPresent()) {
			throw new RecipeNotFoundException(id);
		}
		recipeRepository.deleteById(id);
	}

	/**
	 *this method is tp update recipe by id.
	 */
	@Override
	public void update(Recipe recipe, int recipeId) {
		String recipeName = recipe.getName();
		recipe.setId(recipeId);
		if(!recipeRepository.findById(recipeId).isPresent()) {
			throw new RecipeNotFoundException(recipeId);
		}
		/*if(null != recipeRepository.findByName(recipeName.strip())) {
			throw new RecipeAlreadExistsException(recipeName);
		}*/
		recipeRepository.save(recipe);
	}

	/**
	 *this method is to get recipe based on type.
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
