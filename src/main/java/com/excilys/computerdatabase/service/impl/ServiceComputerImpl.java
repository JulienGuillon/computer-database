package com.excilys.computerdatabase.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.exception.PersistenceException;
import com.excilys.computerdatabase.pagination.Page;
import com.excilys.computerdatabase.persistence.CrudComputer;
import com.excilys.computerdatabase.persistence.DatabaseManager;
import com.excilys.computerdatabase.service.ServiceComputer;

/**
 * @author jlng
 *
 * 2017-03-02
 */

@Service
@Transactional
public class ServiceComputerImpl implements ServiceComputer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceComputerImpl.class);

    @Autowired
    private CrudComputer crudComputer;
    
    @Autowired
    private DatabaseManager databaseManager;

    /**
     *
     * @param optionalComputer :
     *
     */
    public void create(Optional<Computer> optionalComputer) {
        Connection connection = databaseManager.getConnection();
        try {
            crudComputer.create(connection, optionalComputer);
            databaseManager.commit();
        } catch (SQLException e) {
            databaseManager.rollback();
            throw new PersistenceException(e);
        } finally {
            databaseManager.closeConnection();
        }
    }

    /**
     *
     * @return all computers in an optional list
     */
    public List<Computer> findAll() {
        List<Computer> computers = new ArrayList();
        try {
            Connection connection = databaseManager.getConnection();
            computers =  crudComputer.findAll(connection);
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            databaseManager.closeConnection();
        }
        return computers;
    }

    /**
     *
     * @param id :
     *
     */
    public void delete(long id) {

        try {
            Connection connection = databaseManager.getConnection();
            crudComputer.delete(connection, id);
            databaseManager.commit();
        } catch (SQLException e) {
            databaseManager.rollback();
            throw new PersistenceException(e);
        } finally {
            databaseManager.closeConnection();
        }
    }

    /**
     *
     * @param optionalComputer :
     *
     */
    public void update(Optional<Computer> optionalComputer) {
        try {
            Connection connection = databaseManager.getConnection();
            crudComputer.update(connection, optionalComputer);
            databaseManager.commit();
        } catch (SQLException e) {
            databaseManager.rollback();
            throw new PersistenceException(e);
        } finally {            
            databaseManager.closeConnection();
        }
    }

    /**
     * @param filter :
     * @return number of row in database
     */
    public int getNumber(String filter) {
        int number = -1;
        try {
            Connection connection = databaseManager.getConnection();
            number = crudComputer.getNumber(connection, filter);
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {            
            databaseManager.closeConnection();
        }
        return number;
    }

    /**
     *
     * @param id :
     * @return a computer found using its id
     */
    public Optional<Computer> find(long id) {
        Optional<Computer> computer = Optional.empty();
        try {
            Connection connection = databaseManager.getConnection();
            computer = crudComputer.find(connection, id);
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {            
            databaseManager.closeConnection();
        }
        return computer;
    }

    /**
     *
     */
    public Page<Computer> getPage(Page<Computer> page) {
        try {
            Connection connection = databaseManager.getConnection();
            page = crudComputer.getPage(connection, page);
        } catch (SQLException e) {
            databaseManager.closeConnection();
            throw new PersistenceException(e);
        }
        return page;
    }

    /**
     * @param selection
     */
    public void multipleDelete(String selection) {
        try {
            Connection connection = databaseManager.getConnection();
            crudComputer.multipleDelete(connection, selection);
            databaseManager.commit();
        } catch (SQLException e) {
            //databaseManager.rollback();
            LOGGER.info(e.toString());
            throw new PersistenceException(e);
        } finally {
            databaseManager.closeConnection();
        }
    }
}
