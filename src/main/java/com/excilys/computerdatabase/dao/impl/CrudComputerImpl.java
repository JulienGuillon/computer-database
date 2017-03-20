package com.excilys.computerdatabase.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerdatabase.dao.DatabaseManager;
import com.excilys.computerdatabase.dao.CrudComputer;
import com.excilys.computerdatabase.dao.mapper.MapperComputer;
import com.excilys.computerdatabase.entities.Computer;
import com.excilys.computerdatabase.exceptions.PersistenceException;
import com.excilys.computerdatabase.services.ServiceCompany;
import com.excilys.computerdatabase.services.ServiceComputer;
import com.excilys.computerdatabase.utils.LoadProperties;

/**
 * @author Guillon Julien
 *
 *         20 févr. 2017
 *
 *         Allows to make all the crud operation on entity computer
 */

public enum CrudComputerImpl implements CrudComputer {
    INSTANCE;

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

    private DatabaseManager databaseManager = DatabaseManager.INSTANCE;

    CrudComputerImpl() {
        loadProperties.setFileName("queries.properties");
        loadProperties.initLoadProperties();
        properties = loadProperties.getProperties();
    }


    /**
     * @param id :
     * @return an optional computer
     */
    public Optional<Computer> find(long id) {
        Optional<Computer> computer = Optional.empty();
        try (Connection connection = databaseManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(properties.getProperty(SELECT_COMPUTER_BY_ID));) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    computer = MapperComputer.resultSetToComputer(Optional.ofNullable(resultSet));
                } else {
                    LOGGER.info("Id doesn't match any computer in database");
                }
            }
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
        return computer;
    }

    /**
     * @return an Optional ResultSet
     */
    public List<Computer> findAll() {

        List<Computer> computers = new ArrayList<>();
        try (Connection connection = databaseManager.getConnection();
                Statement statement = connection.createStatement();) {
            try (ResultSet resultSet = statement.executeQuery(properties.getProperty(SELECT_COMPUTERS))) {
                while (resultSet.next()) {
                    if (MapperComputer.resultSetToComputer(Optional.ofNullable(resultSet)).isPresent()) {
                        computers.add(MapperComputer.resultSetToComputer(Optional.ofNullable(resultSet)).get());
                    }
                }
            }
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
        return computers;
    }

    /**
     * Delete a computer on database.
     * @param id :
     */
    public void delete(long id) {


        try (Connection connection = databaseManager.getConnection();
            PreparedStatement preparedStatementDelete = connection.prepareStatement(properties.getProperty(DELETE_COMPUTER_BY_ID));) {
            preparedStatementDelete.setLong(1, id);
            if (preparedStatementDelete.executeUpdate() == 0) {
                LOGGER.info("This id doesn't match any computer in database");
            }
            databaseManager.commit();
        } catch (SQLException e) {
            databaseManager.rollback();
            throw new PersistenceException(e);
        }
    }

    /**
     * Update a computer on database.
     *
     * @param optionalComputer :
     *
     */
    public void update(Optional<Computer> optionalComputer) {
        if (optionalComputer.isPresent()) {
            Computer computer = optionalComputer.get();
            long id = computer.getId();

            try (Connection connection = databaseManager.getConnection();
                PreparedStatement preparedStatementUpdate = connection.prepareStatement(properties.getProperty(UPDATE_COMPUTER_BY_ID));) {
                preparedStatementUpdate.setString(1, computer.getName());
                preparedStatementUpdate.setDate(2,
                        computer.getIntroduced() != null ? Date.valueOf(computer.getIntroduced()) : null);
                preparedStatementUpdate.setDate(3,
                        computer.getDiscontinued() != null ? Date.valueOf(computer.getDiscontinued()) : null);
                if (computer.getManufacturer() != null) {
                    preparedStatementUpdate.setLong(4, computer.getManufacturer().getId());
                } else {
                    preparedStatementUpdate.setNull(4, Types.BIGINT);
                }
                preparedStatementUpdate.setLong(5, id);
                preparedStatementUpdate.execute();
                databaseManager.commit();
            } catch (SQLException e) {
                databaseManager.rollback();
                throw new PersistenceException(e);
            }
        }
    }

    /**
     * Persist a computer on database.
     *
     * @param optionalComputer :
     *
     */
    public void create(Optional<Computer> optionalComputer) {
        if (optionalComputer.isPresent()) {
            Computer computer = optionalComputer.get();

            try (Connection connection = databaseManager.getConnection();
                PreparedStatement preparedStatementInsert = connection.prepareStatement(properties.getProperty(INSERT_COMPUTER));) {
                preparedStatementInsert.setString(1, computer.getName());
                preparedStatementInsert.setObject(2, computer.getIntroduced());
                preparedStatementInsert.setObject(3, computer.getDiscontinued());
                if (computer.getManufacturer() != null) {
                    preparedStatementInsert.setLong(4, computer.getManufacturer().getId());
                } else {
                    preparedStatementInsert.setNull(4, Types.BIGINT);
                }
                preparedStatementInsert.execute();
                databaseManager.commit();
            } catch (SQLException e) {
                databaseManager.rollback();
                throw new PersistenceException(e);
            }
        }
    }

    /**
     * Find computers using pagination on database.
     *
     * @return an Optional list of computer
     */
    public List<Computer> findUsingPagination(int size, int offset, String name) {
        List<Computer> computers = new ArrayList<>();

        try (Connection connection = databaseManager.getConnection();
            PreparedStatement preparedStatementPagination = connection.prepareStatement(properties.getProperty(PAGINATION_COMPUTERS));) {
            preparedStatementPagination.setString(1, "%" + name + "%");
            preparedStatementPagination.setInt(2, size);
            preparedStatementPagination.setInt(3, offset);
            try (ResultSet resultSet = preparedStatementPagination.executeQuery();) {
                while (resultSet.next()) {
                    if (MapperComputer.resultSetToComputer(Optional.ofNullable(resultSet)).isPresent()) {
                        computers.add(MapperComputer.resultSetToComputer(Optional.ofNullable(resultSet)).get());
                    }
                }
            }
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return computers;
    }

    /* (non-Javadoc)
     * @see com.excilys.computerdatabase.dao.Crud#getNumber()
     */
    @Override
    public int getNumber(String filter) {

        int number = 0;
        try (Connection connection = databaseManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(properties.getProperty(SELECT_COMPUTERS_NUMBER));){
            statement.setString(1, "%" + filter + "%");
            try (ResultSet resultSet = statement.executeQuery();) {
                resultSet.next();
                number = resultSet.getInt("number");
            }
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
        return number;
        }

}
