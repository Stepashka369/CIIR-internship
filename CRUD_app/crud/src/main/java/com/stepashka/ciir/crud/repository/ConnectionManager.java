package com.stepashka.ciir.crud.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

//Singleton class for getting one connection with database
public class ConnectionManager {
	private static ConnectionManager instance;
	private static String DATABASE_DRIVER;
	private static String URL; 
	private static String USERNAME;
	private static String PASSWORD;
	private Connection connection;
	
	private ConnectionManager() {
		try {
			this.loadDataBaseInfo();
			this.connect();
		} catch(ConfigurationException exception) {
			//TODO loging
		} catch(ClassNotFoundException exception) {
			//TODO loging
		} catch(SQLException exception) {
			//TODO loging
		}		
	}
	
	private void loadDataBaseInfo() throws ConfigurationException{
		PropertiesConfiguration config = new PropertiesConfiguration();
		config.load("application.properties");
		DATABASE_DRIVER = config.getString("database_driver");
		URL = config.getString("database_url");
		USERNAME = config.getString("database_name");
		PASSWORD = config.getString("database_password");
	}
	
	private void connect() throws ClassNotFoundException, SQLException {
		Class.forName(DATABASE_DRIVER); //load JDBC driver
		this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); //connect to server
	}
	
	private void disconnect() throws SQLException{
		connection.close();
	}
	
	public static ConnectionManager getInstance() {
		if(instance == null) {
			instance = new ConnectionManager();
		}
		return instance;
	}
	
	public Connection openConnection() {
		return connection;
	}
	
	public void closeConnection() {
		try {
			this.disconnect();
		} catch(SQLException exception) {
			//TODO loging
		}
	}
}
