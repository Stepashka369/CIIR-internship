package com.stepashka.crud;

import java.io.File;

import org.apache.log4j.PropertyConfigurator;
import com.stepashka.crud.entity.Client;
import com.stepashka.crud.repository.ClientDao;

public class App {
	 public static void main( String[] args ) {
		PropertyConfigurator.configure("G:\\studies\\CIIR\\CIIR-internship\\src\\main\\resources\\logging.properties");
	    Client client = new Client();
	    client.setFirstName("Ivan");
	    client.setLastName("Kardash");	
	   	client.setPhoneNumber("375445345123");
	   	client.setAddress("Have no information");
	   	ClientDao clientRepository = new ClientDao();
	   	clientRepository.create(client);
	   	//ConnectionManager.getInstance().closeConnection();
	    System.out.println();
	 }
}
