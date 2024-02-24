package com.stepashka.crud;

import org.apache.log4j.PropertyConfigurator;

import com.stepashka.crud.entity.Client;
import com.stepashka.crud.repository.ClientDao;
import com.stepashka.crud.repository.ConnectionManager;

public class App {
	 public static void main( String[] args ) {
		org.apache.log4j.BasicConfigurator.configure();
		//PropertyConfigurator.configure("G:\\studies\\CIIR\\CIIR-internship\\src\\main\\resources\\logging.properties");
	    Client client = new Client();
	    client.setFirstName("Ivan");
	    client.setLastName("Kardash");
	   	client.setPhoneNumber("375445345123");
	   	client.setAddress("Have no information");
	   	ClientDao clientRepository = new ClientDao();
	   	clientRepository.create(client);
	   	//ConnectionManager.getInstance().closeConnection();
	    System.out.println( "Hello World!" );
	 }
}
