
package com.org.recipe.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.org.recipe.dto.IngredientsDTO;
import com.org.recipe.dto.RecipeDTO;
import com.org.recipe.model.Ingredients;
import com.org.recipe.model.Recipe;
import com.org.recipe.service.RecipeService;
import com.org.recipe.util.RecipeUtil;

class RecipeControllerTest {

	@InjectMocks
	RecipeController recipeController;

	@Autowired
	RecipeService recipeService;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	Recipe recipe;

	@Mock
	Ingredients ingredients;

	@Mock
	RecipeUtil recipeUtil;

	@Mock
	RecipeDTO recipeDto;

	@Mock
	IngredientsDTO ingredientsDTO;

	private final String URL = "/recipes/";

	@BeforeEach
	void setUp() {
		recipe = Mockito.mock(Recipe.class);
		ingredients = Mockito.mock(Ingredients.class);
		recipeService = Mockito.mock(RecipeService.class);
		recipeDto = Mockito.mock(RecipeDTO.class);
		ingredientsDTO = Mockito.mock(IngredientsDTO.class);
		recipeUtil = Mockito.mock(RecipeUtil.class);

	}

	@Test
	void testGetAllRecipes() {

		// prepare data and mock's behaviour
		List<Recipe> recipes = buildRecipes();
		when(recipeService.getAllRecipe()).thenReturn(recipes);

		assertEquals("recipes", recipeController.getAllRecipes());

	}

	/**
	* 
	*/
	@Test
	void createRecipeTest() {

		List<RecipeDTO> recipes = buildRecipeDTO();
		String recipeName = "Biriyani";
		Mockito.when(recipeUtil.convertToEntity(recipeDto)).thenReturn(recipe);
		recipeService.save(recipe);
		assertEquals("recipe got created", recipeController.createRecipe(recipeDto));

	}

	private List<Recipe> buildRecipes() {
		List<Recipe> recipes = new ArrayList<>();
		recipe.setDescription("fruit icecream");
		recipe.setId(1);
		recipe.setImagePath("next to sea food");
		recipe.setName("kiwi pinnaple ice cream");
		recipe.setType("veg");
		recipe.setNumberOfServings(2);
	//	recipe.setRecipeDate(LocalDateTime.now());
		List<Ingredients> ingredientsList = new ArrayList<>();
		ingredients.setId(2);
		ingredients.setName("2 onions");
		ingredients.setQuantity("3");
		ingredientsList.add(ingredients);
		recipe.setIngredients(ingredientsList);
		recipes.add(recipe);
		return recipes;
	}

	private List<RecipeDTO> buildRecipeDTO() {
		List<RecipeDTO> recipeDtoList = new ArrayList<>();
		recipeDto.setDescription("fruit icecream");
		recipeDto.setId(1);
		recipeDto.setImagePath("next to sea food");
		recipeDto.setName("kiwi pinnaple ice cream");
		recipeDto.setType("veg");
		recipeDto.setNumberOfServings(2);
	//	recipeDto.setRecipeDate(LocalDateTime.now());
		List<IngredientsDTO> ingredientsDtoList = new ArrayList<>();
		ingredientsDTO.setId(2);
		ingredientsDTO.setName("2 onions");
		ingredientsDTO.setQuantity("3");
		ingredientsDtoList.add(ingredientsDTO);
		recipeDto.setIngredientsDto(ingredientsDtoList);
		recipeDtoList.add(recipeDto);
		return recipeDtoList;
	}

}
