package business;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.Interceptors;

import beans.Inventory;
import beans.Item;
import data.DataAccessInterface;
import data.InventoryDataService;
import util.LoggingInterceptor;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors(LoggingInterceptor.class)
public class InventoryManager implements InventoryManagerInterface{

	// DI of the Data Access layer
	@EJB
	DataAccessInterface<Inventory> dao;
	
	// DI of the logger EJB Singleton
	@EJB 
	Logger logger;
	
	
	public Inventory addItem(Inventory inventory, String itemName, int quantity, double cost) {
		logger.logEnter("addItem");
		Item item = new Item(itemName, quantity, cost);
		dao.createItem(item);
		logger.logExit("addItem");
		return dao.findBy(inventory);
	}
	
	public Inventory addInventory(Inventory inventory) {
		logger.logEnter("addInventory");
		dao.create(inventory);
		logger.logExit("addInventory");
		return dao.findBy(inventory);
	}
	
	public Inventory getInventory(String inventoryName) {
		return dao.findBy(new Inventory(inventoryName));
	}
}
