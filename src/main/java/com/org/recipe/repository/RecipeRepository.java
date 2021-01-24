package com.org.recipe.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.org.recipe.model.Recipe;

/**
 * This class represents data layer.
 * @author smita
 *
 */
@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Integer>{

	/**
	 * This method to find recipe by name.
	 * @param name
	 * @return Recipe
	 */
	Recipe findByName(String name);
	
	/**
	 * This method is to find list of recipes by type
	 * @param type
	 * @return List<Recipe>
	 */
	List<Recipe> findAllByType(String type);

}
