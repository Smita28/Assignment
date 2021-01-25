package com.org.recipe.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.org.recipe.dto.IngredientsDTO;
import com.org.recipe.dto.RecipeDTO;
import com.org.recipe.model.Ingredients;
import com.org.recipe.model.Recipe;
import com.org.recipe.service.RecipeService;
import com.org.recipe.util.RecipeUtil;
import com.org.recipe.util.TestUtil;

/**
 * This class contains test scenario for Recipe Controller
 * @author smita
 *
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(RecipeController.class)
@WithMockUser(username = "admin", password = "password", roles = "USER")
class RecipeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	RecipeService recipeService;

	@MockBean
	RecipeDTO recipeDTO;
	
	@MockBean
	RecipeUtil recipeUtil;

	private final String URL = "/recipes/";

	/**
	 * This method explains a success test for create recipe
	 * @throws Exception
	 */
	@Test
	void testCreateRecipeSuccess() throws Exception {

		// Given
		RecipeDTO recipeDTO = buildRecipeDTO();
		Recipe recipe = buildRecipe();
		//when
		when(recipeService.save(any(Recipe.class))).thenReturn(recipe);
		when(recipeUtil.convertToEntity(any(RecipeDTO.class))).thenReturn(recipe);
		when(recipeUtil.convertToDto(any(Recipe.class))).thenReturn(recipeDTO);

		// then execute
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON).content(TestUtil.objectToJson(recipeDTO)))

				.andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals(HttpStatus.CREATED.value(), status, "CREATED");

		// verify that service method was called once
		verify(recipeService, times(1)).save(any(Recipe.class));

		RecipeDTO recipeDTO1 = TestUtil.jsonToObject(result.getResponse().getContentAsString(), RecipeDTO.class);
		assertNotNull(recipeDTO1);
		assertEquals("kiwi pinnaple ice cream", recipeDTO1.getName());

	}

	/**
	 * This method contains success test for get all recipes.
	 * @throws Exception
	 */
	@Test
	 void testGetAllRecipesSuccess() throws Exception {
		
        //Given
		List<RecipeDTO> recipeDTOList = buildRecipeDtoList();
		RecipeDTO recipeDTO = buildRecipeDTO();
		List<Recipe> recipeList = buildRecipeList();
		//when
		when(recipeService.getAllRecipe()).thenReturn(recipeList);
	    when(recipeUtil.convertToDto(any(Recipe.class))).thenReturn(recipeDTO);
		

		// then execute 
	    MvcResult result=mockMvc.perform(MockMvcRequestBuilders.get(URL).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(TestUtil.objectToJson(recipeDTOList)))

				.andReturn();

		// verify int status = result.getResponse().getStatus();
		int status = result.getResponse().getStatus();
		assertEquals(HttpStatus.OK.value(), status, "OK");

		// verify that service method was called once
		verify(recipeService, times(1)).getAllRecipe();

		
		// RecipeDTO recipeDTO1 = TestUtil.jsonToList(result.getResponse().getContentAsString(),recipeDTO1);
		  
		 

	}

	/**
	 * This method is the success test for updating a recipe by id.
	 * @throws Exception
	 */
	@Test
	void testUpdateRecipeSuccess() throws Exception {

		// Given
		int recipeId = 1;
		RecipeDTO recipeDTO = buildRecipeDTO();
		Recipe recipe = buildRecipe();
		//when
		when(recipeService.update(any(Recipe.class), any(Integer.class))).thenReturn(recipe);
		when(recipeUtil.convertToEntity(any(RecipeDTO.class))).thenReturn(recipe);
		when(recipeUtil.convertToDto(any(Recipe.class))).thenReturn(recipeDTO);

		// then execute
		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.put(URL + "/{recipeId}", recipeId).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON).content(TestUtil.objectToJson(recipeDTO)))
				.andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals(HttpStatus.OK.value(), status, "OK");

		// verify that service method was called once
		verify(recipeService, times(1)).update(any(Recipe.class), any(Integer.class));

		RecipeDTO recipeDTO1 = TestUtil.jsonToObject(result.getResponse().getContentAsString(), RecipeDTO.class);
		assertNotNull(recipeDTO1);
		assertEquals("kiwi pinnaple ice cream", recipeDTO1.getName());

	}

	/**
	 * success test for get recipe by id.
	 * @throws Exception
	 */
	@Test
	void testGetRecipeeSuccess() throws Exception {

		// Given
		int recipeId = 1;
		RecipeDTO recipeDTO = buildRecipeDTO();
		Recipe recipe = buildRecipe();
		//when
		when(recipeService.getRecipeById(any(Integer.class))).thenReturn(recipe);
		when(recipeUtil.convertToDto(any(Recipe.class))).thenReturn(recipeDTO);

		// then execute
		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.get(URL + "/{recipeId}", recipeId).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON).content(TestUtil.objectToJson(recipeDTO)))
				.andReturn();

		// then
		int status = result.getResponse().getStatus();
		assertEquals(HttpStatus.OK.value(), status, "OK");

		// verify that service method was called once
		verify(recipeService, times(1)).getRecipeById(any(Integer.class));

		RecipeDTO recipeDTO1 = TestUtil.jsonToObject(result.getResponse().getContentAsString(), RecipeDTO.class);
		assertNotNull(recipeDTO1);
		assertEquals("kiwi pinnaple ice cream", recipeDTO1.getName());

	}

	/**
	 * This method is for success test for delete recipe.
	 * @throws Exception
	 */
	@Test
	void testDeleteRecipeSuccess() throws Exception {

		// Given
		int recipeId = 1;
		RecipeDTO recipeDTO = buildRecipeDTO();

		// when
		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.delete(URL + "/{recipeId}", recipeId).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON).content(TestUtil.objectToJson(recipeDTO)))
				.andReturn();

		// then
		int status = result.getResponse().getStatus();
		assertEquals(HttpStatus.NO_CONTENT.value(), status, "No Content");

		// verify that service method was called once
		verify(recipeService, times(1)).delete(any(Integer.class));

	}


	/**
	 * This method does stubs for recipe.
	 * @return Recipe
	 */
	private Recipe buildRecipe() {
		Recipe recipe = new Recipe();
		recipe.setDescription("fruit icecream");
		recipe.setId(1);
		recipe.setImagePath("next to sea food");
		recipe.setName("kiwi pinnaple ice cream");
		recipe.setType("veg");
		recipe.setNumberOfServings(2);
		// recipe.setRecipeDate(new Date());
		List<Ingredients> ingredientsList = new ArrayList<>();
		Ingredients ingredients = new Ingredients();
		ingredients.setId(2);
		ingredients.setName("2 onions");
		ingredients.setQuantity("3");
		ingredientsList.add(ingredients);
		recipe.setIngredients(ingredientsList);
		return recipe;
	}

	/**
	 * This method does stubs for RecipeDTO.
	 * @return RecipeDTO
	 */
	private RecipeDTO buildRecipeDTO() {
		RecipeDTO recipeDto = new RecipeDTO();
		recipeDto.setDescription("fruit icecream");
		recipeDto.setId(1);
		recipeDto.setImagePath("next to sea food");
		recipeDto.setName("kiwi pinnaple ice cream");
		recipeDto.setType("veg");
		recipeDto.setNumberOfServings(2);
		// recipeDto.setRecipeDate(new Date());
		List<IngredientsDTO> ingredientsDtoList = new ArrayList<>();
		IngredientsDTO ingredientsDTO = new IngredientsDTO();
		ingredientsDTO.setId(2);
		ingredientsDTO.setName("2 onions");
		ingredientsDTO.setQuantity("3");
		ingredientsDtoList.add(ingredientsDTO);
		recipeDto.setIngredientsDto(ingredientsDtoList);
		return recipeDto;
	}

	/**
	 * This method does stubs for recipe list
	 * @return List<Recipe>
	 */
	private List<Recipe> buildRecipeList() {
		List<Recipe> recipeList = new ArrayList<>();
		Recipe recipe = new Recipe();
		recipe.setDescription("fruit icecream");
		recipe.setId(1);
		recipe.setImagePath("next to sea food");
		recipe.setName("kiwi pinnaple ice cream");
		recipe.setType("veg");
		recipe.setNumberOfServings(2);
		// recipe.setRecipeDate(new Date());
		List<Ingredients> ingredientsList = new ArrayList<>();
		Ingredients ingredients = new Ingredients();
		ingredients.setId(2);
		ingredients.setName("2 onions");
		ingredients.setQuantity("3");
		ingredientsList.add(ingredients);
		recipe.setIngredients(ingredientsList);
		recipeList.add(recipe);

		recipe = new Recipe();
		recipe.setDescription("stir fried vegetables");
		recipe.setId(4);
		recipe.setImagePath("stir fried vegetables Image");
		recipe.setName("Spring Roll");
		recipe.setType("veg");
		recipe.setNumberOfServings(3);
		// recipe.setRecipeDate(new Date());
		ingredientsList = new ArrayList<>();
		ingredients = new Ingredients();
		ingredients.setId(5);
		ingredients.setName("1 caroot");
		ingredients.setQuantity("6");
		ingredientsList.add(ingredients);
		recipe.setIngredients(ingredientsList);
		recipeList.add(recipe);

		return recipeList;
	}

	/**
	 * This method does stubs for recipe DTO list
	 * @return List<RecipeDTO>
	 */
	private List<RecipeDTO> buildRecipeDtoList() {
		List<RecipeDTO> recipeDTOList = new ArrayList<>();
		RecipeDTO recipeDto = new RecipeDTO();
		recipeDto.setDescription("fruit icecream");
		recipeDto.setId(1);
		recipeDto.setImagePath("next to sea food");
		recipeDto.setName("kiwi pinnaple ice cream");
		recipeDto.setType("veg");
		recipeDto.setNumberOfServings(2);
		// recipe.setRecipeDate(new Date());
		List<IngredientsDTO> ingredientsDtoList = new ArrayList<>();
		IngredientsDTO ingredientsDto = new IngredientsDTO();
		ingredientsDto.setId(2);
		ingredientsDto.setName("2 onions");
		ingredientsDto.setQuantity("3");
		ingredientsDtoList.add(ingredientsDto);
		recipeDto.setIngredientsDto(ingredientsDtoList);
		recipeDTOList.add(recipeDto);

		recipeDto = new RecipeDTO();
		recipeDto.setDescription("stir fried vegetables");
		recipeDto.setId(4);
		recipeDto.setImagePath("stir fried vegetables Image");
		recipeDto.setName("Spring Roll");
		recipeDto.setType("veg");
		recipeDto.setNumberOfServings(3);
		// recipe.setRecipeDate(new Date());
		ingredientsDtoList = new ArrayList<>();
		ingredientsDto = new IngredientsDTO();
		ingredientsDto.setId(5);
		ingredientsDto.setName("1 caroot");
		ingredientsDto.setQuantity("6");
		ingredientsDtoList.add(ingredientsDto);
		recipeDto.setIngredientsDto(ingredientsDtoList);
		recipeDTOList.add(recipeDto);

		return recipeDTOList;
	}

}
