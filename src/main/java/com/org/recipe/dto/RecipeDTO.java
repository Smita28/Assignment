package com.org.recipe.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class RecipeDTO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String description;
	private String imagePath;
	private String name;
	private String type;
	private int quantity;
	private LocalDateTime recipeDate = LocalDateTime.now();
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getRecipeDate() {
		return recipeDate;
	}

	public void setRecipeDate(LocalDateTime recipeDate) {
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
