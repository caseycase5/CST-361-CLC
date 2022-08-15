package business;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import beans.Inventory;
import beans.Item;
import data.InventoryDataService;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class InventoryManager {

	InventoryDataService service = new InventoryDataService();
	
	public Inventory addItem(Inventory inventory, String itemName, int quantity, double cost) {
		Item item = new Item(itemName, quantity, cost);
		service.createItem(item);
		return inventory;
	}
	
	public Inventory addInventory(Inventory inventory) {
		service.create(inventory);
		return service.findBy(inventory);
	}
}
