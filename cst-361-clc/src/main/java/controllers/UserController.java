// Created by Casey Huz for CST-361
package controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import beans.User;

@ManagedBean
@ViewScoped
public class UserController {
	public String onRegister(User user) {
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		// This will hold code to link to a "User Manager" class that will push the registration to the SQL server
		// For now, this just fakes the registration and returns the input to show it gathers the data.
		System.out.println("Registration success!");
		return "Login.xhtml";
	}
	
	public String onLogin(User user) {
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		// This will hold code to compare inputted data to that stored on an SQL server. If the data matches
		// the user will be logged in, otherwise they will be returned to the login page.
		// For now, this is simulated using a default username/password combo.
		boolean x = user.validateLogin(user.username, user.password);
		if(x) {
			return "InventoryHome.xhtml";
		}
		else {
			System.out.println("Login Failed!");
			System.out.println(x);
			System.out.println("username: " + user.username);
			System.out.println("Password: " + user.password);
			return "Login.xhtml";
		}
	}
}
