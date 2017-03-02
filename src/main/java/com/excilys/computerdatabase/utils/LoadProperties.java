package com.excilys.computerdatabase.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerdatabase.exceptions.PersistenceException;

/**
 * @author Guillon Julien
 *
 *         23 févr. 2017
 */
public enum LoadProperties {
    INSTANCE;

    private final Logger LOGGER = LoggerFactory.getLogger(LoadProperties.class);

    private Properties properties;

    private static final String FILENAME = "database.properties";

    /**
     *
     */
    LoadProperties() {
        properties = new Properties();
        InputStream input = null;
        input = LoadProperties.class.getClassLoader().getResourceAsStream(FILENAME);
        if (input == null) {
            LOGGER.error("Sorry, unable to find " + FILENAME);
            throw new PersistenceException("Unable to acces config file at " + FILENAME);
        }
        try {
            properties.load(input);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Properties getProperties() {
        return properties;
    }

}
