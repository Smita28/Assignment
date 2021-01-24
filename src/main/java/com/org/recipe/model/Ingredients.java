package com.org.recipe.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * This class is having ingridient property which is to create a recipe.
 * @author smita
 *
 */
@Entity(name = "Ingredients")
public class Ingredients implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
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
	public Ingredients() {}
	public Ingredients(int id, String quantity, String name) 
	{
		super();
		this.id = id;
		this.quantity = quantity;
		this.name = name;
		
	}
	
	
	
}
