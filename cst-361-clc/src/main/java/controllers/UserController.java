// Created by Casey Huz for CST-361
package controllers;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.interceptor.Interceptors;

import beans.Inventory;
import beans.User;
import business.Logger;
import data.InventoryDataService;
import util.LoggingInterceptor;

@Named
@ViewScoped
@Interceptors(LoggingInterceptor.class)
public class UserController implements Serializable {
	// DI of the logger EJB Singleton
	@EJB 
	Logger logger;
	
	public String onRegister(User user) {
		logger.logEnter("onRegister");
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		// This will hold code to link to a "User Manager" class that will push the registration to the SQL server
		// For now, this just fakes the registration and returns the input to show it gathers the data.
		System.out.println("Registration success!");
		logger.logExit("onRegister");
		return "Login.xhtml";
	}
	
	public String onLogin(User user) {
		logger.logEnter("onLogin");
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		// This will hold code to compare inputted data to that stored on an SQL server. If the data matches
		// the user will be logged in, otherwise they will be returned to the login page.
		// For now, this is simulated using a default username/password combo.
		boolean x = user.validateLogin(user.username, user.password);
		if(x) {
			InventoryDataService service = new InventoryDataService();
			Inventory inventory = service.findBy(new Inventory("Test Inventory"));
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("inventory", inventory);
			logger.logExit("onLogin");
			return "InventoryHome.xhtml";
		}
		else {
			System.out.println("Login Failed!");
			System.out.println(x);
			System.out.println("username: " + user.username);
			System.out.println("Password: " + user.password);
			logger.logExit("onLogin");
			return "Login.xhtml";
		}
	}
}
