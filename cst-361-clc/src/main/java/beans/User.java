// Created by Casey Huz for CST-361
package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ManagedBean
@ViewScoped
public class User {
	
	// Variables
	public int id;
	@NotNull()
	@Size(min=1, max=50)
	public String name;
	@NotNull()
	@Size(min=4, max=50)
	public String username;
	@NotNull()
	@Size(min=8, max=50)
	public String password;
	
	// Constructors	
	public User() {
		id = 0;
		name = "";
		username = "";
		password = "";
	}
	
	// Validate info placeholder for SQL server data.
	public boolean validateLogin(String username, String password) {
		if(username.equals("test") && password.equals("password")) {
			return true;
		}
		else {
			return false;
		}
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
