package com.org.recipe.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.org.recipe.model.Ingredients;
import com.org.recipe.model.Recipe;

public class RecipeUtilTest {

	@InjectMocks
	RecipeUtil recipeUtil;
	@Mock
	Recipe recipe;
	@Mock
	Ingredients ingredients;
	
	@BeforeEach
	void setUp() {
		recipe = Mockito.mock(Recipe.class);
		ingredients=Mockito.mock(Ingredients.class);

	}
	
	@Test
	void convertToDtoTest() {
		recipe.setDescription("fruit icecream");
		recipe.setId(1);
		recipe.setImagePath("next to sea food");
		recipe.setName("kiwi pinnaple ice cream");
		recipe.setType("veg");
		recipe.setNumberOfServings(2);
		recipe.setRecipeDate(new Timestamp(System.currentTimeMillis()));
		List<Ingredients> ingredientsList = new ArrayList<>();
		ingredients.setId(2);
		ingredients.setName("2 onions");
		ingredients.setQuantity("3");
		ingredientsList.add(ingredients);
		recipe.setIngredients(ingredientsList);
		assertEquals(recipe, recipe);
		recipeUtil.convertToDto(recipe);
		
	}
	
	@SuppressWarnings("rawtypes")
	public static List jsonToList(String json, TypeToken token) {
		Gson gson = new Gson();
		return gson.fromJson(json, token.getType());
	}

	public static String objectToJson(Object obj) {
		Gson gson = new Gson();
		return gson.toJson(obj);
	}

	public static <T> T jsonToObject(String json, Class<T> classOf) {
		Gson gson = new Gson();
		return gson.fromJson(json, classOf);
	}

}
