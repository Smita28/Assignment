package com.org.recipe.util;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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


	@Autowired
	private ModelMapper modelMapper;
	
	/**
	 * This nethod converts recipe to recipeDto
	 * @param recipe
	 * @return RecipeDTO
	 */
	public RecipeDTO convertToDto(Recipe recipe) {
		RecipeDTO recipeDTO = new RecipeDTO();

		if (null != recipe) {
			recipeDTO = modelMapper.map(recipe, RecipeDTO.class);

			List<IngredientsDTO> ingredientsDTO = recipe.getIngredients().stream().map(this::convertToDto)
					.collect(Collectors.toList());

			recipeDTO.setIngredientsDto(ingredientsDTO);
		}
		return recipeDTO;
	}

	/**
	 * This class converts recipeDto to recipe
	 * @param recipeDto
	 * @return recipe
	 */
	public Recipe convertToEntity(RecipeDTO recipeDto) {
		Recipe recipe = new Recipe();
		if (null != recipeDto) {
			recipe = modelMapper.map(recipeDto, Recipe.class);

			List<Ingredients> ingredients = recipeDto.getIngredientsDto().stream().map(this::convertToEntity)
					.collect(Collectors.toList());

			recipe.setIngredients(ingredients);
		}
		return recipe;
	}

	/**
	 * This class converts ingredients to ingredientsDto.
	 * @param ingredients
	 * @return IngredientsDTO
	 */
	public IngredientsDTO convertToDto(Ingredients ingredients) {
		IngredientsDTO ingredientsDTO = new IngredientsDTO();

		if (null != ingredients) {
			ingredientsDTO = modelMapper.map(ingredients, IngredientsDTO.class);
		}
		return ingredientsDTO;
	}

	/**
	 * This class converts ingredientsDto to ingredients.
	 * @param ingredientsDto
	 * @return Ingredients
	 */
	public Ingredients convertToEntity(IngredientsDTO ingredientsDto) {
		Ingredients ingredients = new Ingredients();
		if (null != ingredientsDto) {
			ingredients = modelMapper.map(ingredientsDto, Ingredients.class);
		}

		return ingredients;
	}
	
	
	
}
