package com.org.recipe.controller;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.org.recipe.dto.RecipeDTO;
import com.org.recipe.model.Recipe;
import com.org.recipe.service.RecipeService;
import com.org.recipe.util.RecipeUtil;

/**
 * This class implements rest full services for recipe service.
 * 
 * @author smita
 */

@RestController
@RequestMapping("/")
@Validated
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
	 * @param 
	 */

	@GetMapping("/recipes")
	public ResponseEntity<List<RecipeDTO>> getAllRecipes() {
		logger.info("Inside getAllrecipe method ");
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
	public ResponseEntity<RecipeDTO> getRecipe(@Valid @PathVariable("recipeId") int recipeId) {
		logger.info("Inside getRecipe method ");
		return new ResponseEntity<>(recipeUtil.convertToDto(recipeService.getRecipeById(recipeId)),HttpStatus.OK);
	} 

	/**
	 * This method is to create a new recipe.
	 * 
	 * @param recipeDto
	 * @return ResponseEntity<Object>
	 */
	@PostMapping("/recipes")
	public ResponseEntity<RecipeDTO> createRecipe(@Valid @RequestBody RecipeDTO recipeDto) {
		ResponseEntity<RecipeDTO> response = null;
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
		Recipe recipe = recipeService.update(recipeUtil.convertToEntity(recipeDto),recipeId);
		response = new ResponseEntity<>(recipeUtil.convertToDto(recipe), HttpStatus.OK);
		return response;
	}

	/**
	 * This method is to delete recipe based on id.
	 * 
	 * @param int recipeId
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
	 * @param String recipeType
	 * @return ResponseEntity<List<RecipeDTO>>
	 */
	
	@GetMapping("/recipesByType/{type}")
	public ResponseEntity<List<RecipeDTO>> getAllRecipesByType(@PathVariable("type") String recipeType) {
		logger.info("Inside getAllRecipesByType method ");
		List<Recipe> recipes = recipeService.getRecipesByType(recipeType);
		List<RecipeDTO> recipesDto = recipes.stream().map(recipeUtil::convertToDto).collect(Collectors.toList());
		return new ResponseEntity<>(recipesDto, HttpStatus.OK);

	}
    /**
     * This method handles validation error response.
     * @param MethodArgumentNotValidException ex
     * @return Map<String, String>
     */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
     
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage()));
         
        return errors;
    }
}
	


