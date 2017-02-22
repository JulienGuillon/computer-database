package com.excilys.computerdatabase.dao;

/**
 * @author Guillon Julien
 *
 * 20 févr. 2017
 *
 * Contains all constants to access to database
 */
public class ConstanteDatabase {
	
	public static final String CLASS_NAME = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost";
	public static final String PORT = "3306";
	public static final String DB_NAME = "computer-database-db";
	public static final String USER = "admincdb";
	public static final String PASSWORD = "qwerty1234";
	public static final String CONNECTION = URL + ":" + PORT + "/" + DB_NAME +"?zeroDateTimeBehavior=convertToNull";
}
