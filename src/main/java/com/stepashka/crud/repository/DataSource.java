package com.stepashka.crud.repository;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSource {
	private static final Logger logger = Logger.getLogger(DataSource.class);
	private static HikariConfig config = new HikariConfig();
	private static HikariDataSource dataSource;
	private static String url; 
	private static String username;
	private static String password;
	private static Integer maxPoolSize;
	
	static {
		loadDatabaseInfo();
		config.setJdbcUrl(url);
	    config.setUsername(username);
	    config.setPassword(password);
	    config.setMaximumPoolSize(maxPoolSize);
	    dataSource = new HikariDataSource(config);
	}

	private DataSource() {
	}
	
	public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
	
	private static void loadDatabaseInfo() {
		try {
			PropertiesConfiguration config = new PropertiesConfiguration();
			config.load("application.properties");
			url = config.getString("datasource.url");
			username = config.getString("datasource.username");
			password = config.getString("datasource.password");
			maxPoolSize = config.getInteger("datasource.maximum-pool-size", 1);
		} catch(ConfigurationException exception) {
			logger.error(exception.getMessage());
		}
	}
}