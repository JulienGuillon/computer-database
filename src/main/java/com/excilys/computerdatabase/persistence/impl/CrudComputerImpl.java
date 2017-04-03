package com.excilys.computerdatabase.persistence.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.exception.PersistenceException;
import com.excilys.computerdatabase.pagination.Page;
import com.excilys.computerdatabase.persistence.CrudComputer;
import com.excilys.computerdatabase.persistence.DatabaseManager;
import com.excilys.computerdatabase.persistence.Datasource;
import com.excilys.computerdatabase.persistence.mapper.ComputerRowMapper;
import com.excilys.computerdatabase.util.LoadProperties;
import com.mysql.fabric.xmlrpc.base.Array;

/**
 * @author Guillon Julien
 *
 * 20 févr. 2017
 *
 * Allows to make all the crud operation on entity computer
 */

@Repository
public class CrudComputerImpl implements CrudComputer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrudComputerImpl.class);

    private LoadProperties loadProperties = LoadProperties.INSTANCE;

    private Properties properties;

    private static final String SELECT_COMPUTERS = "SELECT_COMPUTERS";
    private static final String SELECT_COMPUTER_BY_ID = "SELECT_COMPUTER_BY_ID";
    private static final String PAGINATION_COMPUTERS = "PAGINATION_COMPUTERS";
    private static final String DELETE_COMPUTER_BY_ID = "DELETE_COMPUTER_BY_ID";
    private static final String UPDATE_COMPUTER_BY_ID = "UPDATE_COMPUTER_BY_ID";
    private static final String INSERT_COMPUTER = "INSERT_COMPUTER";
    private static final String SELECT_COMPUTERS_NUMBER = "SELECT_COMPUTERS_NUMBER";
    private static final String DELETE_COMPUTERS = "DELETE_COMPUTERS";

    @Autowired
    private DatabaseManager databaseManager;

    @Autowired
    private Datasource dataSource;
    
    private JdbcTemplate jdbcTemplateObject;

    @PostConstruct
    public void setDataSource() {

       this.jdbcTemplateObject = new JdbcTemplate(dataSource);

    }

    public CrudComputerImpl() {
        loadProperties.initLoadProperties("queries.properties");
        properties = loadProperties.getProperties();
    }

    /**
     * @param id :
     * @return an optional computer
     */
    public Optional<Computer> find(Connection connection, long id) {
        Computer computer = null;
        try {    
            computer = (Computer) jdbcTemplateObject.queryForObject(properties.getProperty(SELECT_COMPUTER_BY_ID), new Object[] {id}, new ComputerRowMapper());
        } catch (DataAccessException dataAccessException) {
            throw new PersistenceException(dataAccessException);
        }
        return Optional.ofNullable(computer);
    }

    /**
     * @return an Optional ResultSet
     */
    public List<Computer> findAll(Connection connection) {
        List<Computer> computers = new ArrayList<>();
        try {
            computers = jdbcTemplateObject.query(SELECT_COMPUTERS, new BeanPropertyRowMapper(Computer.class));
        } catch (DataAccessException dataAccessException) {
            throw new PersistenceException(dataAccessException);
        }
        return computers;
    }

    /**
     * Delete a computer on database.
     * @param id :
     */
    public void delete(Connection connection, long id) {
        try {
            jdbcTemplateObject.update(properties.getProperty(DELETE_COMPUTER_BY_ID), new Object[] {id}, Computer.class);
        } catch (DataAccessException dataAccessException) {
            throw new PersistenceException(dataAccessException);
        }
    }

    /**
     * Update a computer on database.
     *
     * @param optionalComputer :
     *
     */
    public void update(Connection connection, Optional<Computer> optionalComputer) {
        try {
            if (optionalComputer.isPresent()) {
                Computer computer = optionalComputer.get();
                jdbcTemplateObject.update(properties.getProperty(UPDATE_COMPUTER_BY_ID), computer.getName(),
                        computer.getIntroduced() != null ? Date.valueOf(computer.getIntroduced()) : null,
                                computer.getDiscontinued() != null ? Date.valueOf(computer.getDiscontinued()) : null,
                                        computer.getManufacturer() != null ? computer.getManufacturer().getId() : null,
                                                computer.getId());
            }
        } catch (DataAccessException dataAccessException) {
            throw new PersistenceException(dataAccessException);
        }
    }

    /**
     * Persist a computer on database.
     *
     * @param optionalComputer :
     * @throws SQLException 
     *
     */
    public void create(Connection connection, Optional<Computer> optionalComputer) throws SQLException {
        
        try {
            if (optionalComputer.isPresent()) {
                Computer computer = optionalComputer.get();
                
                jdbcTemplateObject.update(properties.getProperty(INSERT_COMPUTER), computer.getName(), computer.getIntroduced(),
                        computer.getDiscontinued(), computer.getManufacturer().getId());
            }
        } catch (DataAccessException dataAccessException) {
            throw new PersistenceException(dataAccessException);
        }
    }

    /* (non-Javadoc)
     * @see com.excilys.computerdatabase.dao.Crud#getNumber()
     */
    @Override
    public int getNumber(Connection connection, String filter) {
        int number;
        try {   
            number = jdbcTemplateObject.queryForObject(properties.getProperty(SELECT_COMPUTERS_NUMBER), new Object[] {filter + "%"}, Integer.class);
        } catch (DataAccessException dataAccessException) {
            throw new PersistenceException(dataAccessException);
        }
        return number;
    }

    @Override
    public Page<Computer> getPage(Connection connection, Page<Computer> page) {
        List<Computer> computers = new ArrayList<>();
        try {
            computers = (List<Computer>) jdbcTemplateObject.query(properties.getProperty(PAGINATION_COMPUTERS), new ComputerRowMapper(),
                    page.getFilter()+"%", page.getElementsByPage(), page.getPage()*page.getElementsByPage());
            page.setElements(computers);
            page.setTotalElements(getNumber(connection, page.getFilter()));
        } catch (DataAccessException dataAccessException) {
            throw new PersistenceException(dataAccessException);
        }
        return page;
    }
    
    @Override
    public void multipleDelete(Connection connection, String selection) {
        try {
            jdbcTemplateObject.update(properties.getProperty(DELETE_COMPUTERS)+selection+");");
        } catch (DataAccessException dataAccessException) {
            throw new PersistenceException(dataAccessException);
        }
    }

}
