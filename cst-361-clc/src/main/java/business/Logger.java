package business;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.interceptor.InvocationContext;

@Singleton
@Startup
public class Logger{
	public void logEnter(String currentMethod) {
		System.out.println("Entering the " + currentMethod + " method.");
	}
	
	public void logExit(String currentMethod) {
		System.out.println("Exiting the " + currentMethod + " method.");
	}
}
