package com.org.recipe.dto;

import java.io.Serializable;


/**
 * This class is having all the ingredients used in creating a recipe.
 * @author smita
 *
 */
public class IngredientsDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String quantity;
	private String name;
	 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public IngredientsDTO() {
		
	}
	public IngredientsDTO(int id, String quantity, String name) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.name = name;
	}
	
}
