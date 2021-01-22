package com.org.recipe.dto;

import java.io.Serializable;


public class IngredientsDTO implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int amount;
	private String name;
	 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public IngredientsDTO() {
		
	}
	public IngredientsDTO(int id, int amount, String name) {
		super();
		this.id = id;
		this.amount = amount;
		this.name = name;
	}
	
}
