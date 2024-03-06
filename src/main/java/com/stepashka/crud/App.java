package com.stepashka.crud;

import org.apache.log4j.PropertyConfigurator;

public class App {
	 public static void main( String[] args ) {
		PropertyConfigurator.configure("G:\\studies\\CIIR\\CIIR-internship\\src\\main\\resources\\logging.properties");
		Menu menu = new Menu();
		menu.mainMenu();
	 }
}
