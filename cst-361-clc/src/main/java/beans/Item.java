// Created by Casey Huz for CST-361
package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@ManagedBean
@ViewScoped
public class Item {
	
	// Variables
	public int id;
	@NotNull()
	public String name;
	@NotNull()
	@Digits(message="Invalid Number", integer = 8, fraction = 0)
	public int quantity;
	@NotNull()
	@Digits(message="Invalid Number", integer = 6, fraction = 2)
	public double cost;
	
	// Constructors
	public Item() {
		id = 0;
		name = "";
		quantity = 0;
		cost = 0.00;
	}
	
	public Item(String name, int quantity, double cost) {
		this.name = name;
		this.quantity = quantity;
		this.cost = cost;
	}
	
	public Item(int id, String name, int quantity, double cost) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.cost = cost;
	}
	
	// Getters and Setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
}
