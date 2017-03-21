package com.excilys.computerdatabase.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.exceptions.PersistenceException;
import com.excilys.computerdatabase.utils.LoadProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @author Guillon Julien
 *
 * 20 févr. 2017
 *
 * Singleton that consist to manage all operations concerning database connection
 *
 */

@Repository
public class DatabaseManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseManager.class);

    private Connection connection;

    private LoadProperties loadProperties = LoadProperties.INSTANCE;

    private Properties properties;

    private HikariDataSource dataSource;

    /**
     *
     */
    public DatabaseManager() {
        loadProperties.setFileName("hikari.properties");
        loadProperties.initLoadProperties();
        properties = loadProperties.getProperties();
        HikariConfig config = new HikariConfig(properties);
        config.setConnectionTestQuery("show tables");
        dataSource = new HikariDataSource(config);
    }

    /**
     * @return the connect
     * @throws PersistenceException
     */
    public Connection getConnection() {
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new PersistenceException("Error on openning connection to database", e);
        }
        LOGGER.info("Connection to database successfully effectued");
        return connection;
    }

    /**
     *
     */
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new PersistenceException("Error on close database connection " + e);
        }
    }

    /**
     *
     */
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new PersistenceException("Error on commit on database " + e);
        }
    }

    /**
     *
     */
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new PersistenceException("Error for rollback on database " + e);
        }
    }

}
