package com.excilys.computerdatabase.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerdatabase.utils.LoadProperties;

/**
 * @author Guillon Julien
 *
 * 20 févr. 2017
 * 
 * Singleton that consist to manage all operations concerning
 * database connection
 * 
 */

public enum DatabaseManager {
	INSTANCE;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseManager.class);
	
	private Connection connection;
	
	private static final String DRIVER = "db.driver";
	
	private static final String URL = "db.url";

	private static final String USER = "db.user";

	private static final String PASSWORD = "db.password";

	private Properties properties = LoadProperties.INSTANCE.getProperties();
	
	private DatabaseManager()
	{
	
		try {
			Class.forName(properties.getProperty(DRIVER));
			//connection = DriverManager.getConnection(properties.getProperty(URL), properties.getProperty(USER), properties.getProperty(PASSWORD));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @return the connect
	 */
	public Connection getConnection() {
		try {
			connection = DriverManager.getConnection(properties.getProperty(URL), properties.getProperty(USER), properties.getProperty(PASSWORD));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	public void closeConnection()
	{
		try {
			connection.close();
		} catch (SQLException e) {
			LOGGER.error("Error on close database connection " + e);
			e.printStackTrace();
		}
	}

}
