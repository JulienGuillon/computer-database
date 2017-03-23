package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.exception.PersistenceException;
import com.excilys.computerdatabase.util.LoadProperties;
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

    @Autowired
    private Datasource datasource;
    
    //private JdbcTemplate jdbcTemplate;
/**
    @Autowired
    public void setDataSource(Datasource dataSource) {
        this.jdbcTemplate = new JdbcTemplate();
        this.jdbcTemplate.setDataSource(dataSource);
    }
**/


    /**
     * @return the connect
     * @throws PersistenceException
     */
    public Connection getConnection() {
        try {
            connection = datasource.getConnection();
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
