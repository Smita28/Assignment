package com.org.recipe.controller;



import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
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
import com.org.recipe.util.RecipeUtilTest;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RecipeController.class)
class RecipeControllerTest2 {

	  @Autowired
	    private MockMvc mockMvc;
	  
	  @MockBean
	  RecipeService recipeService;
	  @MockBean
	  RecipeUtil recipeUtil;
	
	
    private final String URL = "/recipes/";

    @WithMockUser(value = "USER")
    @Test
    public void testcreateRecipe() throws Exception {

        // prepare data and mock's behaviour
    	RecipeDTO recipeDTO  = buildRecipeDTO();
    	Recipe recipe  = buildRecipe();
        when(recipeService.save(recipe)).thenReturn(recipe);

        // execute
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8).content(RecipeUtilTest.objectToJson(recipeDTO)))
        	
        		.andReturn();

        // verify
        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.CREATED.value(), status, "CREATED");

        // verify that service method was called once
        verify(recipeService).save(recipe);

        RecipeDTO recipeDTO1 = RecipeUtilTest.jsonToObject(result.getResponse().getContentAsString(), RecipeDTO.class);
        assertNotNull(recipeDTO1);
        assertEquals("kiwi pinnaple ice cream", recipeDTO1.getName());

    }

	private List<Recipe> buildRecipes() {
		List<Recipe> recipes = new ArrayList<>();
		Recipe recipe = new Recipe();
		recipe.setDescription("fruit icecream");
		recipe.setId(1);
		recipe.setImagePath("next to sea food");
		recipe.setName("kiwi pinnaple ice cream");
		recipe.setType("veg");
		recipe.setNumberOfServings(2);
		recipe.setRecipeDate(LocalDateTime.now());
		List<Ingredients> ingredientsList = new ArrayList<>();
		Ingredients ingredients = new Ingredients();
		ingredients.setId(2);
		ingredients.setName("2 onions");
		ingredients.setQuantity("3");
		ingredientsList.add(ingredients);
		recipe.setIngredients(ingredientsList);
		recipes.add(recipe);
		return recipes;
	}
	private List<RecipeDTO> buildRecipesDTO() {
		List<RecipeDTO> recipeDtoList = new ArrayList<>();
		RecipeDTO recipeDto = new RecipeDTO();
		recipeDto.setDescription("fruit icecream");
		recipeDto.setId(1);
		recipeDto.setImagePath("next to sea food");
		recipeDto.setName("kiwi pinnaple ice cream");
		recipeDto.setType("veg");
		recipeDto.setNumberOfServings(2);
		recipeDto.setRecipeDate(LocalDateTime.now());
		List<IngredientsDTO> ingredientsDtoList = new ArrayList<>();
		IngredientsDTO ingredientsDTO = new IngredientsDTO();
		ingredientsDTO.setId(2);
		ingredientsDTO.setName("2 onions");
		ingredientsDTO.setQuantity("3");
		ingredientsDtoList.add(ingredientsDTO);
		recipeDto.setIngredientsDto(ingredientsDtoList);
		recipeDtoList.add(recipeDto);
		return recipeDtoList;
	}
	
	private Recipe buildRecipe() {
		Recipe recipe = new Recipe();
		recipe.setDescription("fruit icecream");
		recipe.setId(1);
		recipe.setImagePath("next to sea food");
		recipe.setName("kiwi pinnaple ice cream");
		recipe.setType("veg");
		recipe.setNumberOfServings(2);
		recipe.setRecipeDate(LocalDateTime.now());
		List<Ingredients> ingredientsList = new ArrayList<>();
		Ingredients ingredients = new Ingredients();
		ingredients.setId(2);
		ingredients.setName("2 onions");
		ingredients.setQuantity("3");
		ingredientsList.add(ingredients);
		recipe.setIngredients(ingredientsList);
		return recipe;
	}
	private RecipeDTO buildRecipeDTO() {
		RecipeDTO recipeDto = new RecipeDTO();
		recipeDto.setDescription("fruit icecream");
		recipeDto.setId(1);
		recipeDto.setImagePath("next to sea food");
		recipeDto.setName("kiwi pinnaple ice cream");
		recipeDto.setType("veg");
		recipeDto.setNumberOfServings(2);
		recipeDto.setRecipeDate(LocalDateTime.now());
		List<IngredientsDTO> ingredientsDtoList = new ArrayList<>();
		IngredientsDTO ingredientsDTO = new IngredientsDTO();
		ingredientsDTO.setId(2);
		ingredientsDTO.setName("2 onions");
		ingredientsDTO.setQuantity("3");
		ingredientsDtoList.add(ingredientsDTO);
		recipeDto.setIngredientsDto(ingredientsDtoList);
		return recipeDto;
	}

}
