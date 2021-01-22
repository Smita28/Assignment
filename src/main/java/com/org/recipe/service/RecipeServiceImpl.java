package com.org.recipe.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.recipe.exception.RecipeNotFoundException;
import com.org.recipe.model.Recipe;
import com.org.recipe.repository.RecipeRepository;
/**
 * This class is implementation class of RecipeDTO service.
 * @author smita
 *
 */
@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	RecipeRepository recipeRepository;

	@Override
	public List<Recipe> getAllRecipe() {
		List<Recipe> recipe = new ArrayList<>();
		recipeRepository.findAll().forEach(recipe1 -> recipe.add(recipe1));
		return recipe;
	}

	@Override
	public Recipe getRecipeById(int id) {
		return recipeRepository.findById(id)
			      .orElseThrow(() -> new RecipeNotFoundException(id));
	}

	@Override
	public void saveOrUpdate(Recipe recipe) {
		recipeRepository.save(recipe);
	}

	@Override
	public void delete(int id) {
		recipeRepository.deleteById(id);
	}

	@Override
	public void update(Recipe recipe, int recipeId) {
		recipeRepository.save(recipe);
	}
	
}
