package com.org.recipe.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.org.recipe.dto.IngredientsDTO;
import com.org.recipe.dto.RecipeDTO;
import com.org.recipe.model.Ingredients;
import com.org.recipe.model.Recipe;
import com.org.recipe.repository.RecipeRepository;
import com.org.recipe.util.RecipeUtil;

class RecipeServiceImplTest {

	@InjectMocks
	RecipeServiceImpl recipeServiceImpl;
	@Autowired
	RecipeRepository recipeRepository;
	@Mock
	Recipe recipe;
	@Mock
	Ingredients ingredients;
	
	@BeforeEach
	void setUp() {
		recipe = Mockito.mock(Recipe.class);
		ingredients = Mockito.mock(Ingredients.class);
		//recipeRepository = Mockito.mock(RecipeRepository.class);

		/*
		 * recipeService = Mockito.mock(RecipeService.class); recipeDto =
		 * Mockito.mock(RecipeDTO.class); ingredientsDTO =
		 * Mockito.mock(IngredientsDTO.class); recipeUtil =
		 * Mockito.mock(RecipeUtil.class);
		 */

	}
	
	@Test
	void testGetAllRecipe() {
		List<Recipe> allRecipes = buildRecipes();
		assertEquals(allRecipes, allRecipes, "recipes are here");
		when(recipeRepository.findAll()).thenReturn(allRecipes);
		recipeServiceImpl.getAllRecipe();
	}
	@Test
	void testGetRecipeById() {
		int id=1;
		//when(recipeRepository.findById(id)).thenReturn(recipe);
		recipeServiceImpl.getRecipeById(id);
	}

	@Test
	void testSave(){

		String recipeName="Khichdi";
		when(recipeRepository.findByName(recipeName)).thenReturn(recipe);
		recipeServiceImpl.save(recipe);
	}
	private List<Recipe> buildRecipes() {
		List<Recipe> recipes = new ArrayList<>();
		recipe.setDescription("fruit icecream");
		recipe.setId(1);
		recipe.setImagePath("next to sea food");
		recipe.setName("kiwi pinnaple ice cream");
		recipe.setType("veg");
		recipe.setNumberOfServings(2);
		//recipe.setRecipeDate(LocalDateTime.now());
		List<Ingredients> ingredientsList = new ArrayList<>();
		ingredients.setId(2);
		ingredients.setName("2 onions");
		ingredients.setQuantity("3");
		ingredientsList.add(ingredients);
		recipe.setIngredients(ingredientsList);
		recipes.add(recipe);
		return recipes;
	}
}
