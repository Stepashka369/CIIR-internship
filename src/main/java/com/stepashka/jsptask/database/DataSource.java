package com.stepashka.jsptask.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    private static final Logger logger = Logger.getLogger(DataSource.class);
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource hikariDataSource;
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
        hikariDataSource = new HikariDataSource(config);
    }

    private DataSource() {
    }

    public static Connection getConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }

    private static void loadDatabaseInfo() {
        try {
            Class.forName("org.postgresql.Driver");
            PropertiesConfiguration config = new PropertiesConfiguration();
            config.load("G:\\studies\\CIIR\\task3(maven)\\JspTask\\src\\main\\resources\\application.properties");
            url = config.getString("datasource.url");
            username = config.getString("datasource.username");
            password = config.getString("datasource.password");
            maxPoolSize = config.getInteger("datasource.maximum-pool-size", 1);
        } catch (ConfigurationException | ClassNotFoundException exception) {
            logger.error(exception.getMessage());
        }
    }
}