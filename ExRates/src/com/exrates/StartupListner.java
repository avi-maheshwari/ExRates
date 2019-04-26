package com.exrates;

import java.util.Timer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StartupListner implements ServletContextListener{
	
	ServletContext context;
	public void contextInitialized(ServletContextEvent contextEvent) {
		System.out.println("StartupListner.contextInitialized() : Context Created");
		ExchangeRateSchedular exRateSchObj =new ExchangeRateSchedular();
		
		Timer t=new Timer();
		t.scheduleAtFixedRate(exRateSchObj, 5,5*60*1000);
		System.out.println("StartupListner.contextInitialized() : Live Data Fetch Scheduled at evry 5 Mins");
		
	}
	public void contextDestroyed(ServletContextEvent contextEvent) {
		context = contextEvent.getServletContext();
		System.out.println("Context Destroyed");
	}
}
