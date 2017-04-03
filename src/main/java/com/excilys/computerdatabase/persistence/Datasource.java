package com.excilys.computerdatabase.persistence;

import java.util.Properties;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.util.LoadProperties;

/**
 * @author Guillon Julien
 *
 * 23 mars 2017
 */


@Repository
@Scope("singleton")
public class Datasource extends DriverManagerDataSource {

    private LoadProperties loadProperties = LoadProperties.INSTANCE;

    private Properties properties;
    
    private static final String DRIVER = "db.driver";
    private static final String URL = "db.url";
    private static final String USER = "db.user";
    private static final String PASSWORD = "db.password";
    
    /**
     * Configures data source.
     */
    public Datasource() {
        loadProperties.initLoadProperties("database.properties");
        properties = loadProperties.getProperties();
        
        this.setDriverClassName(properties.getProperty(DRIVER));
        this.setUrl(properties.getProperty(URL));
        this.setUsername(properties.getProperty(USER));
        this.setPassword(properties.getProperty(PASSWORD));
    }
}