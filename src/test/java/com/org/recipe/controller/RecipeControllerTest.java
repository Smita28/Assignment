package com.org.recipe.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito.Then;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.org.recipe.model.Recipe;
import com.org.recipe.service.RecipeService;

class RecipeControllerTest {

	@InjectMocks
	RecipeController recipeController;
	@Mock
	RecipeService recipeService;
	@Mock
	Recipe recipe;
	
	
	  @Test void testGetAllRecipe() {
	  
	  List<Recipe> recipes = new ArrayList<>();
	  recipe.setDescription("spicy n salty chicken");
	  recipe.setId(1);
	  recipe.setName("chilli chicken");
	  Mockito.when(recipeService.getAllRecipe()).thenReturn(recipes);
	  recipeController.getAllrecipe();
	  }
	 

}
