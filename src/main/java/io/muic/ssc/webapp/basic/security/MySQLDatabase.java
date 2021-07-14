package io.muic.ssc.webapp.basic.security;

import io.muic.ssc.webapp.basic.config.ConfigProperties;
import io.muic.ssc.webapp.basic.config.ConfigurationLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDatabase {

    private static MySQLDatabase service;

    public Connection getConnection() {
        try {
            ConfigProperties configProperties = ConfigurationLoader.load();

            if (configProperties == null) {
                throw new RuntimeException("Unable to read the config.properties");
            }

            String jdbcDriver =  configProperties.getDatabaseDriverClassName();
            String jdbcURL =  configProperties.getDatabaseConnectionUrl();
            String username = configProperties.getDatabaseUsername();
            String password = configProperties.getDatabasePassword();
            Class.forName(jdbcDriver);

            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            return connection;

        } catch (SQLException | ClassNotFoundException e) {
            return null;
        }
    }

    public static MySQLDatabase getInstance() {
        if (service == null) {
            service = new MySQLDatabase();
        }
        return service;
    }
}
