package com.org.recipe.controller;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.recipe.dto.IngredientsDTO;
import com.org.recipe.dto.RecipeDTO;
import com.org.recipe.model.Ingredients;
import com.org.recipe.model.Recipe;
import com.org.recipe.service.RecipeService;
/**
 * This class produces recipe rest services
 * @author smita
 */

@RestController
@RequestMapping("/${super.root.path}")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RecipeController {

	@Autowired
	RecipeService recipeService;

	@Autowired
	private ModelMapper modelMapper;
	
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
	/**
	 * This method is to get all the recipe object.
	 * @return ResponseEntity<List<RecipeDTO>>
	 * @param null
	 */
	@GetMapping("/${super.getAllRecipe.path}")
	public ResponseEntity<List<RecipeDTO>> getAllrecipe() {
		logger.info("Inside getrecipe method ");
		List<Recipe> recipes = recipeService.getAllRecipe();
		List<RecipeDTO> recipesDto = recipes.stream()
											.map(this::convertToDto)
											.collect(Collectors.toList());
		return new ResponseEntity<>(recipesDto, HttpStatus.OK);
	}

	/**
	 *  This method is to get recipe as per id.
	 * @param recipeId
	 * @return
	 */
	@GetMapping("/${super.getRecipe.path}")
	public RecipeDTO getRecipe(@PathVariable("recipeId") int recipeId) {
		logger.info("Inside getRecipe method ");
		return convertToDto(recipeService.getRecipeById(recipeId));
	}

	/**
	 * This method is to create a new recipe.
	 * @param recipe
	 * @return
	 */
	@PostMapping("/${super.saveRecipe.path}")
	public ResponseEntity<Object> saveRecipe(@RequestBody RecipeDTO recipeDto) {
		ResponseEntity<Object> response = null;
		logger.info("Inside saverecipe method ");
				
				recipeService.saveOrUpdate(convertToEntity(recipeDto));
				response = new ResponseEntity<>(recipeDto.getId(),HttpStatus.OK);
			
		return response;
	}

	/**
	 * This method is to update a recipe.
	 * @param recipe
	 * @return ResponseEntity<Object>
	 */
	@PutMapping("/${super.updateRecipe.path}")
	public ResponseEntity<RecipeDTO> updateRecipe(@RequestBody RecipeDTO recipeDto, @PathVariable("recipeId") int recipeId) {
		ResponseEntity<RecipeDTO> response = null;
		logger.info("Inside update method ");
			recipeService.saveOrUpdate(convertToEntity(recipeDto));
			response = new ResponseEntity<>(recipeDto,HttpStatus.OK);
		return response;
	}

	/**
	 * This method is to delete recipe.
	 * @param recipeId
	 * @return void
	 */
	@DeleteMapping("/${super.deleterecipe.path}")
	public ResponseEntity<RecipeDTO> deleterecipe(@PathVariable("recipeId") int recipeId) {
		ResponseEntity<RecipeDTO> response = null;
		logger.info("Inside deleterecipe method ");
		recipeService.delete(recipeId);
		response = new ResponseEntity<>(HttpStatus.OK);
		return response;
	}
	
	
	
	
	
	private RecipeDTO convertToDto(Recipe recipe) {
		RecipeDTO recipeDTO = new RecipeDTO();

		if (null != recipe) {
			recipeDTO = modelMapper.map(recipe, RecipeDTO.class);
			
			List<IngredientsDTO> ingredientsDTO = recipe.getIngredients().stream()
					.map(this::convertToDto)
					.collect(Collectors.toList());
			
			recipeDTO.setIngredientsDto(ingredientsDTO);
		}
		return recipeDTO;
	}
	
	private Recipe convertToEntity(RecipeDTO recipeDto) {
		Recipe recipe = new Recipe();
		if (null != recipeDto) {
			recipe = modelMapper.map(recipeDto, Recipe.class);
		
		List<Ingredients> ingredients = recipeDto.getIngredientsDto().stream()
				.map(this::convertToEntity)
				.collect(Collectors.toList());
		
		recipe.setIngredients(ingredients);
	}
		return recipe;
	}
	
	private IngredientsDTO convertToDto(Ingredients ingredients) {
		IngredientsDTO ingredientsDTO = new IngredientsDTO();

		if (null != ingredients) {
			ingredientsDTO = modelMapper.map(ingredients, IngredientsDTO.class);
		}
		return ingredientsDTO;
	}
	
	private Ingredients convertToEntity(IngredientsDTO ingredientsDto) {
		Ingredients ingredients = new Ingredients();
		if (null != ingredientsDto) {
			ingredients = modelMapper.map(ingredientsDto, Ingredients.class);
		}

		return ingredients;
	}
}
