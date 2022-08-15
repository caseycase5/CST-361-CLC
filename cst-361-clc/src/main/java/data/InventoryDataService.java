// Created by Casey Huz for CST-361
package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import beans.Inventory;
import beans.Item;
/**
 * Session Bean implementation class MusicDataService
 */
@Stateless
@Local(DataAccessInterface.class)
@LocalBean
public class InventoryDataService implements DataAccessInterface<Inventory> {
	 /**
     * Default constructor. 
     */
    public InventoryDataService() 
    {
    }

    /**
     * CRUD: finder to return a single entity
     */
	public Inventory findById(int id)
	{
		return null;
	}

    /**
     * CRUD: finder to return all entities
     * @throws DatabaseException 
     */
    public List<Inventory> findAll() {
		// DB Connection Info
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/cst-361-clc";
		String username = "root";
		String password = "SIVnlwqo112!!";
		
		// Get all Albums and Tracks
		List<Inventory> inventories = new ArrayList<Inventory>();
		try 
		{
			// Connect to the Database
			conn = DriverManager.getConnection(url, username, password);
			
			// Execute SQL Query and loop over result set
			String sql1 = "SELECT * FROM INVENTORIES";
			Statement stmt1 = conn.createStatement();
			ResultSet rs1 = stmt1.executeQuery(sql1);
			while(rs1.next())
			{
				// Get the Album
				Inventory inventory = new Inventory(rs1.getString("NAME"), rs1.getInt("NUMOFITEMS"));
				
				// Query for all the Albums Tracks
				List<Item> items = new ArrayList<Item>();
				String sql2 = "SELECT * FROM ITEMS WHERE INVENTORYID = " + rs1.getInt("ID");
				Statement stmt2 = conn.createStatement();
				ResultSet rs2 = stmt1.executeQuery(sql2);
				while(rs2.next())
				{
					items.add(new Item(rs2.getInt("ID"), rs2.getString("NAME"), rs2.getInt("QUANTITY"), rs2.getDouble("COST")));
				}
				rs2.close();
				stmt2.close();
				
				// Finish populating the Album and add to the return list
				inventory.setItems(items);;
				inventories.add(inventory);
			}
			
			// Cleanup
			rs1.close();
			stmt1.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			// Cleanup Database
			if(conn != null)
			{
				try 
				{
					conn.close();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
		
		// Return list of Albums
		return inventories;
    }

    /**
     * CRUD: finder to return a single entity
     * @throws DatabaseException 
     */
	public Inventory findBy(Inventory inventory) {
		// DB Connection Info
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/cst-361-clc";
		String username = "root";
		String password = "SIVnlwqo112!!";
		System.out.println(url);
		System.out.println(username);
		System.out.println(password);
		
		// Get all Albums and Tracks
		try 
		{
			// Connect to the Database
			conn = DriverManager.getConnection(url, username, password);
			// Execute SQL Query and loop over result set
			String sql1 = String.format("SELECT * FROM INVENTORIES WHERE NAME = '%S'", inventory.getName());
			System.out.println(sql1);
			Statement stmt1 = conn.createStatement();
			ResultSet rs1 = stmt1.executeQuery(sql1);
			if(!rs1.next())
			{
				rs1.close();
				stmt1.close();
				return null;
			}
			
			// Get the Album
			inventory.setName(rs1.getString("NAME"));
			inventory.setNumOfItems(rs1.getInt("NUMOFITEMS"));
			
				
			// Query for all the Inventory Items
			List<Item> items = new ArrayList<Item>();
			String sql2 = "SELECT * FROM ITEMS WHERE INVENTORYID = " + rs1.getInt("ID");
			Statement stmt2 = conn.createStatement();
			ResultSet rs2 = stmt2.executeQuery(sql2);
			while(rs2.next())
			{
				items.add(new Item(rs2.getInt("ID"), rs2.getString("NAME"), rs2.getInt("QUANTITY"), rs2.getDouble("COST")));
				System.out.println(items.get(0).name);
			}
				
			// Finish populating the Album and add to the return list
			inventory.setItems(items);
			
			// Cleanup
			rs2.close();
			stmt2.close();
			rs1.close();
			stmt1.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			// Cleanup Database
			if(conn != null)
			{
				try 
				{
					conn.close();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
		
		// Return Albums
		return inventory;
	}

	/**
	 * CRUD: create an entity
	 * @throws DatabaseException 
	 */
	public boolean create(Inventory inventory) {
		// DB Connection Info
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/cst-361-clc";
		String username = "root";
		String password = "SIVnlwqo112!!";
		
		// Insert Album and Tracks
		try 
		{
			// Connect to the Database
			conn = DriverManager.getConnection(url, username, password);

			// Insert an Album
			String sql1 = String.format("INSERT INTO INVENTORIES(NAME, NUMOFITEMS) VALUES('%s', '%d')", inventory.getName(), inventory.getNumOfItems());
			Statement stmt1 = conn.createStatement();
			stmt1.executeUpdate(sql1);
			
			// Get Auto-Increment PK back
			String sql2= "SELECT LAST_INSERT_ID() AS LAST_ID FROM INVENTORIES";
			ResultSet rs = stmt1.executeQuery(sql2);
			rs.next();
			String inventoryId = rs.getString("LAST_ID");
			rs.close();
			stmt1.close();
			
			// Insert all the Tracks
			Statement stmt2 = conn.createStatement();
			for(Item item : inventory.getItems())
			{
				String sql3 = String.format("INSERT INTO ITEMS(NAME, QUANTITY, COST, INVENTORYID) VALUES('%s', %d, %d, %d)", item.getName(), item.getQuantity(), item.getCost(), Integer.valueOf(inventoryId));
				stmt2.executeUpdate(sql3);
			}
			stmt2.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			// Cleanup Database
			if(conn != null)
			{
				try 
				{
					conn.close();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
		
		// Return OK
		return true;
	}

	@Override
	public boolean update(Inventory inventory) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Inventory inventory) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean createItem(Item item) {
		// DB Connection Info
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/cst-361-clc";
		String username = "root";
		String password = "SIVnlwqo112!!";
		
		// Insert Album and Tracks
		try 
		{
			// Connect to the Database
			conn = DriverManager.getConnection(url, username, password);

			// Insert an Album
			String sql1 = String.format("INSERT INTO ITEMS(NAME, QUANTITY, COST, INVENTORYID) VALUES('%s', '%d', '%f', '1')", item.getName(), item.getQuantity(), item.getCost());
			Statement stmt1 = conn.createStatement();
			stmt1.executeUpdate(sql1);
			
			// Get Auto-Increment PK back
			String sql2= "SELECT LAST_INSERT_ID() AS LAST_ID FROM ITEMS";
			ResultSet rs = stmt1.executeQuery(sql2);
			rs.next();
			String inventoryId = rs.getString("LAST_ID");
			rs.close();
			stmt1.close();
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			// Cleanup Database
			if(conn != null)
			{
				try 
				{
					conn.close();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
		// Return OK
		return true;
	}

}
