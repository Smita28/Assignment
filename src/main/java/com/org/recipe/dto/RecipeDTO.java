package com.org.recipe.dto;

import java.io.Serializable;
import java.util.List;

import com.sun.istack.NotNull;

/**
 * This class is having recipe properties.
 * @author smita
 *
 */
public class RecipeDTO  implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	@NotNull
	private String description;
	private String imagePath;
	@NotNull
	private String name;
	@NotNull
	private String type;
	private int numberOfServings;
	private  String recipeDate;
	
	public int getNumberOfServings() {
		return numberOfServings;
	}

	public void setNumberOfServings(int numberOfServings) {
		this.numberOfServings = numberOfServings;
	}

	public String getRecipeDate() {
		return recipeDate;
	}

	public void setRecipeDate(String recipeDate) {
		this.recipeDate = recipeDate;
	}

	private List<IngredientsDTO> ingredientsDto;
	
	public RecipeDTO() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<IngredientsDTO> getIngredientsDto() {
		return ingredientsDto;
	}

	public void setIngredientsDto(List<IngredientsDTO> ingredientsDto) {
		this.ingredientsDto = ingredientsDto;
	}


	
}
