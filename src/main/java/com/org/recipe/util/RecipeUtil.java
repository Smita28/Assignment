package com.org.recipe.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.org.recipe.dto.IngredientsDTO;
import com.org.recipe.dto.RecipeDTO;
import com.org.recipe.model.Ingredients;
import com.org.recipe.model.Recipe;


/**
 * This class is having util methods which  is to convert entity class properties to Dto class and vice-versa.
 * @author smita
 *
 */
@Component
public class RecipeUtil {

	/**
	 * This method converts recipe to recipeDto
	 * @param recipe
	 * @return RecipeDTO
	 */
	public  RecipeDTO convertToDto(Recipe recipe) {
		RecipeDTO recipeDto = new RecipeDTO();
		List<IngredientsDTO> ingredientsDTO = new ArrayList<>();
		if (null != recipe) {
			recipeDto.setId(recipe.getId());
			recipeDto.setDescription(recipe.getDescription());
			recipeDto.setName(recipe.getName());
			recipeDto.setImagePath(recipe.getImagePath());
			recipeDto.setNumberOfServings(recipe.getNumberOfServings());
			recipeDto.setType(recipe.getType());
			for(Ingredients ingredients :recipe.getIngredients()) {
				IngredientsDTO ingredientsDto = new IngredientsDTO();
				ingredientsDto.setId(ingredients.getId());
				ingredientsDto.setName(ingredients.getName());
				ingredientsDto.setQuantity(ingredients.getQuantity());
				ingredientsDTO.add(ingredientsDto);
			}
			
			recipeDto.setIngredientsDto(ingredientsDTO);
			recipeDto.setRecipeDate(recipe.getRecipeDate().toString());
		}
		return recipeDto;
	}

	/**
	 * This class converts recipeDto to recipe
	 * @param recipeDto
	 * @return recipe
	 */
	public Recipe convertToEntity(RecipeDTO recipeDto) {
		Recipe recipe = new Recipe();
		List<Ingredients> ingredientsList =new ArrayList<>();
		
		if (null != recipeDto) {
			recipe.setId(recipeDto.getId());
			recipe.setDescription(recipeDto.getDescription());
			recipe.setName(recipeDto.getName());
			recipe.setImagePath(recipeDto.getImagePath());
			recipe.setNumberOfServings(recipeDto.getNumberOfServings());
			recipe.setType(recipeDto.getType());
			for(IngredientsDTO ingredientsDTO :recipeDto.getIngredientsDto()) {
				Ingredients ingredients = new Ingredients();
				ingredients.setId(ingredientsDTO.getId());
				ingredients.setName(ingredientsDTO.getName());
				ingredients.setQuantity(ingredientsDTO.getQuantity());
				ingredientsList.add(ingredients);
			}
			recipe.setIngredients(ingredientsList);
			recipe.setRecipeDate(new Timestamp(System.currentTimeMillis()));
		}
		return recipe;
	}
	
}
