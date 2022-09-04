package business;

import beans.Inventory;

public interface InventoryManagerInterface {

	Inventory addItem(Inventory inventory, String itemName, int quantity, double cost);
	
	Inventory addInventory(Inventory inventory);
	
	Inventory getInventory(String inventoryName);
}
