// Created by Casey Huz for CST-361
package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.NotNull;

@ManagedBean
@ViewScoped
public class Inventory {
	
	// Variables
	@NotNull
	public String name;
	public int numOfItems;
	public List<Item> items;
	
	// Constructors
	public Inventory() {
		name = "";
		numOfItems = 0;
		items = new ArrayList<Item>();
	}
	
	public Inventory (String name) {
		this.name = name;
	}
	
	public Inventory(String name, int numOfItems) {
		this.name = name;
		this.numOfItems = numOfItems;
		this.items = new ArrayList<Item>();
	}
	
	public Inventory(String name, int numOfItems, List<Item> items) {
		this.name = name;
		this.numOfItems = numOfItems;
		this.items = items;
	}
	
	// Helper methods
	public void updateItemCount() {
		numOfItems = items.size();
	}

	// Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumOfItems() {
		return numOfItems;
	}

	public void setNumOfItems(int numOfItems) {
		this.numOfItems = numOfItems;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}
