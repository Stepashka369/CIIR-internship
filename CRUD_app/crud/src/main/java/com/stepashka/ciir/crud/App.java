package com.stepashka.ciir.crud;

import java.sql.ResultSet;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.stepashka.ciir.crud.entity.Client;
import com.stepashka.ciir.crud.repository.ClientDao;
import com.stepashka.ciir.crud.repository.ConnectionManager;

public class App {
    public static void main( String[] args ) {
    	Client client = new Client();
    	client.setFirstName("Ivan");
    	client.setLastName("Kardash");
    	client.setPhoneNumber("375445345123");
    	client.setAddress("Have no information");
    	ClientDao clientRepository = new ClientDao();
    	clientRepository.create(client);
    	ConnectionManager.getInstance().closeConnection();
        System.out.println( "Hello World!" );
    }
}
 