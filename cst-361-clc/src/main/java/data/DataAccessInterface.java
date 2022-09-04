// Created by Casey Huz for CST-361
package data;

import java.util.List;

import beans.Inventory;
import beans.Item;

public interface DataAccessInterface<T> {
	public List<T> findAll();
	public T findById(int id);
	public Inventory findBy(Inventory inventory);
	public boolean create(Inventory inventory);
	public boolean update(Inventory inventory);
	public boolean delete(Inventory inventory);
	public boolean createItem(Item item);
}
