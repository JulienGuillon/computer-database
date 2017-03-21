package com.excilys.computerdatabase.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerdatabase.exception.PersistenceException;

/**
 * @author Guillon Julien
 *
 *         23 févr. 2017
 */
public enum LoadProperties {
    INSTANCE;

    private final Logger LOGGER = LoggerFactory.getLogger(LoadProperties.class);

    private Properties properties;

    private String fileName;

    /**
     *
     */
    LoadProperties() {
    }

    /**
     *
     */
    public void initLoadProperties() {
        properties = new Properties();
        InputStream input = null;
        input = LoadProperties.class.getClassLoader().getResourceAsStream(fileName);
        if (input == null) {
            LOGGER.error("Sorry, unable to find " + fileName);
            throw new PersistenceException("Unable to acces config file at " + fileName);
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

    /**
     * @return the filename
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
