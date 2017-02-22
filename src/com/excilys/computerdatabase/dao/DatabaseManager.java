package com.excilys.computerdatabase.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
	
	private Connection connection;
	
	private DatabaseManager()
	{
		try {
			Class.forName(ConstanteDatabase.CLASS_NAME);
			connection = DriverManager.getConnection(ConstanteDatabase.CONNECTION, ConstanteDatabase.USER, ConstanteDatabase.PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @return the connect
	 */
	public Connection getConnection() {
		return connection;
	}

}
