package com.org.recipe.controller;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.recipe.dto.RecipeDTO;
import com.org.recipe.model.Recipe;
import com.org.recipe.service.RecipeService;
import com.org.recipe.util.RecipeUtil;

/**
 * This class produces recipe rest services
 * 
 * @author smita
 */

@RestController
@RequestMapping("/")
public class RecipeController {

	@Autowired
	RecipeService recipeService;

	@Autowired
	private RecipeUtil recipeUtil;

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

	/**
	 * This method is to get all the recipes.
	 * 
	 * @return ResponseEntity<List<RecipeDTO>>
	 * @param null
	 */

	@GetMapping("/recipes")
	public ResponseEntity<List<RecipeDTO>> getAllRecipes() {
		logger.info("Inside getrecipe method ");
		List<Recipe> recipes = recipeService.getAllRecipe();
		List<RecipeDTO> recipesDto = recipes.stream().map(recipeUtil::convertToDto).collect(Collectors.toList());
		return new ResponseEntity<>(recipesDto, HttpStatus.OK);
	}

	/**
	 * This method is to get recipe as per id.
	 * 
	 * @param recipeId
	 * @return RecipeDTO
	 */
	@GetMapping("/recipes/{recipeId}")
	public RecipeDTO getRecipe(@PathVariable("recipeId") int recipeId) {
		logger.info("Inside getRecipe method ");
		return recipeUtil.convertToDto(recipeService.getRecipeById(recipeId));
	}

	/**
	 * This method is to create a new recipe.
	 * 
	 * @param recipeDto
	 * @return ResponseEntity<Object>
	 */
	@PostMapping("/recipes")
	public ResponseEntity<RecipeDTO> createRecipe(@RequestBody RecipeDTO recipeDto) {
		ResponseEntity<RecipeDTO> response = null;
		RecipeDTO recipeDTO = null;
		logger.info("Inside saverecipe method ");

		Recipe recipe = recipeService.save(recipeUtil.convertToEntity(recipeDto));
		
		response = new ResponseEntity<>(recipeUtil.convertToDto(recipe), HttpStatus.CREATED);

		return response;
	}

	/**
	 * This method is to update a recipe based on recipeId.
	 * 
	 * @param recipeDto,recipeId
	 * @return ResponseEntity<RecipeDTO>
	 */
	@PutMapping("/recipes/{recipeId}")
	public ResponseEntity<RecipeDTO> updateRecipe(@RequestBody RecipeDTO recipeDto,
			@PathVariable("recipeId") int recipeId) {
		ResponseEntity<RecipeDTO> response = null;
		logger.info("Inside update method ");
		recipeService.update(recipeUtil.convertToEntity(recipeDto),recipeId);
		response = new ResponseEntity<>(recipeDto, HttpStatus.OK);
		return response;
	}

	/**
	 * This method is to delete recipe based on id.
	 * 
	 * @param recipeId
	 * @return ResponseEntity<RecipeDTO>
	 */
	@DeleteMapping("/recipes/{recipeId}")
	public ResponseEntity<RecipeDTO> deleteRecipe(@PathVariable("recipeId") int recipeId) {
		ResponseEntity<RecipeDTO> response = null;
		logger.info("Inside deleteRecipe method ");
		recipeService.delete(recipeId);
		response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return response;
	}
	/**
	 * This method is to find recipe based on type(veg,non-veg).
	 * 
	 * @param type
	 * @return ResponseEntity<List<RecipeDTO>>
	 */
	
	@GetMapping("/recipesByType/{type}")
	public ResponseEntity<List<RecipeDTO>> getAllRecipesByType(@PathVariable("type") String recipeType) {
		logger.info("Inside getAllRecipesByType method ");
		List<Recipe> recipes = recipeService.getRecipesByType(recipeType);
		List<RecipeDTO> recipesDto = recipes.stream().map(recipeUtil::convertToDto).collect(Collectors.toList());
		return new ResponseEntity<>(recipesDto, HttpStatus.OK);

	}
	 
}
	


