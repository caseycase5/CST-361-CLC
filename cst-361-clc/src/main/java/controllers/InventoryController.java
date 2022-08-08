// Created by Casey Huz for CST-361
package controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import beans.Inventory;
import beans.Item;
import business.InventoryManager;

@ManagedBean
@ViewScoped
public class InventoryController {
	public String addItem(Inventory inventory, Item item) {
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("inventory", inventory);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("item", item);
		InventoryManager manager = new InventoryManager();
		inventory = manager.addItem(inventory, item.id, item.name, item.quantity, item.cost);
		return "InventoryHome.xhtml";
	}
	
	
}
