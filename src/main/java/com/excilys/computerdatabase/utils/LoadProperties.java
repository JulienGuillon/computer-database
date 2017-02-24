package com.excilys.computerdatabase.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Guillon Julien
 *
 * 23 févr. 2017
 */
public enum LoadProperties {
	INSTANCE;
	
	private Properties properties;
	
	private static final String PATH_DATABASE_PROPERTIES = "/home/excilys/Computer_database/database.properties";
	
	private LoadProperties()
	{
		properties = new Properties();
		try
		{
		  properties.load(new FileInputStream(PATH_DATABASE_PROPERTIES));
		}
		catch (IOException e) {
			
		}
	}
	
	public Properties getProperties()
	{
		return properties;
	}
	
}
