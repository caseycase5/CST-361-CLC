// Created by Casey Huz for CST-361
package controllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import beans.Inventory;
import beans.Item;
import business.InventoryManager;
import business.InventoryManagerInterface;
import business.Logger;
import data.InventoryDataService;

@ManagedBean
@ViewScoped
public class InventoryController {
	
	//dependency injection of Inventory Manager 
	@EJB
	InventoryManagerInterface mgr;
	
	// DI of the logger EJB Singleton
	@EJB 
	Logger logger;
	
	
	public String addItem(Inventory inventory, Item item) {
		logger.logEnter("addItem");
		Inventory updatedInventory = mgr.addItem(inventory, item.name, item.quantity, item.cost);
		updatedInventory = mgr.getInventory("Test Inventory");
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("inventory", updatedInventory);
		logger.logExit("addItem");
		return "InventoryHome.xhtml";
	}
	
	public String addInventory(Inventory inventory) {
		logger.logEnter("addInventory");
		inventory = mgr.addInventory(inventory);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("inventory", inventory);
		logger.logExit("addInventory");
		return "InventoryHome.xhtml";
	}
}
