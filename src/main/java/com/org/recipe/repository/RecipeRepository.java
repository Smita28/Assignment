package com.org.recipe.repository;

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

}
