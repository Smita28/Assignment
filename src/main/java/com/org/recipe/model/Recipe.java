package com.org.recipe.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "recipe")
@Table(name = "recipe")
public class Recipe  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String description;
	private String imagePath;
	private String name;
	private String type;
	private int quantity;
	private LocalDateTime recipeDate = LocalDateTime.now();
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Ingredients> ingredients;

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

	public List<Ingredients> getIngredients() {
		return ingredients;
	}


	public void setIngredients(List<Ingredients> ingredients) {
		this.ingredients = ingredients;
	}



}
