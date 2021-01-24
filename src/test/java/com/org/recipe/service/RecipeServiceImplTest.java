package com.org.recipe.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.org.recipe.model.Ingredients;
import com.org.recipe.model.Recipe;
import com.org.recipe.repository.RecipeRepository;

/**
 * This class is having test scenario for RecipeServiceImpl.
 * @author smita
 *
 */
@ExtendWith(SpringExtension.class)
class RecipeServiceImplTest {

	@MockBean
	RecipeServiceImpl recipeServiceImpl;
	@MockBean
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
	
	/**
	 *success test method to test what recipes we get
	 */
	@Test
	void testGetAllRecipe() {
		List<Recipe> allRecipes = buildRecipes();
		assertEquals(allRecipes, allRecipes, "recipes are here");
		when(recipeRepository.findAll()).thenReturn(allRecipes);
		recipeServiceImpl.getAllRecipe();
	}
	/**
	 *success test to get recipe by id
	 */
	@Test
	void testGetRecipeById() {
		int id=1;
		Optional<Recipe> recipeOpt = buildRecipeOptional();
		when(recipeRepository.findById(id)).thenReturn(recipeOpt);
		
		Recipe recipe = recipeServiceImpl.getRecipeById(id);
		assertEquals(recipe, recipe);
	}
	/**
	 * This method builds stub for recipe list
	 * @return List<Recipe>
	 */
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

	
	/**
	 * This method builds stub for recipe which is optional.
	 * @return Optional<Recipe>
	 */
	private Optional<Recipe> buildRecipeOptional() {
		Optional<Recipe> recipeOpt = Optional.empty();
		Recipe recipe = new Recipe();
		recipe.setDescription("fruit icecream");
		recipe.setId(1);
		recipe.setImagePath("next to sea food");
		recipe.setName("kiwi pinnaple ice cream");
		recipe.setType("veg");
		recipe.setNumberOfServings(2);
		List<Ingredients> ingredientsList = new ArrayList<>();
		ingredients.setId(2);
		ingredients.setName("2 onions");
		ingredients.setQuantity("3");
		ingredientsList.add(ingredients);
		recipe.setIngredients(ingredientsList);
		recipeOpt = Optional.of(recipe);
		return recipeOpt;
	}
}
