package business;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import beans.Inventory;
import beans.Item;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class InventoryManager {

	public Inventory addItem(Inventory inventory, int itemId, String itemName, int quantity, double cost) {
		inventory.items.add(new Item(itemId, itemName, quantity, cost));
		return inventory;
	}
}
