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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerdatabase.dao.DatabaseManager;
import com.excilys.computerdatabase.dao.CrudComputer;
import com.excilys.computerdatabase.dao.mapper.MapperComputer;
import com.excilys.computerdatabase.entities.Computer;
import com.excilys.computerdatabase.exceptions.PersistenceException;
import com.excilys.computerdatabase.services.ServiceComputer;
import com.excilys.computerdatabase.utils.Page;

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

    private static final String SELECT_COMPUTERS = "select computer.id, computer.name, introduced, discontinued, company_id, company.name company_name from computer left join company on company.id = computer.company_id;";
    private static final String SELECT_COMPUTER_BY_ID = "select computer.id, computer.name, introduced, discontinued, company_id, company.name company_name from computer left join company on company.id = computer.company_id where computer.id= ?;";
    private static final String PAGINATION_COMPUTERS = "select computer.id, computer.name, introduced, discontinued, company_id, company.name company_name from computer left join company on company.id = computer.company_id where computer.name like ? limit ? offset ?;";
    private static final String DELETE_COMPUTER_BY_ID = "delete from computer where id = ? ";
    private static final String UPDATE_COMPUTER_BY_ID = "update computer set name = ?, introduced = ?, discontinued = ?, company_id = ? where id = ?";
    private static final String INSERT_COMPUTER = "insert into computer(name, introduced, discontinued, company_id) values (?, ?, ?, ?)";
    private static final String SELECT_COMPUTERS_NUMBER = "select count(*) as number from computer;";

    private DatabaseManager databaseManager = DatabaseManager.INSTANCE;
    private Connection connection;
    private ResultSet resultSet;



    /**
     * @param id :
     * @return an optional computer
     */
    public Optional<Computer> find(long id) {
        Optional<Computer> computer = Optional.empty();
        connection = databaseManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COMPUTER_BY_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                computer = MapperComputer.resultSetToComputer(Optional.ofNullable(resultSet));
            } else {
                LOGGER.info("Id doesn't match any computer in database");
            }
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            databaseManager.closeConnection();
        }
        return computer;
    }

    /**
     * @return an Optional ResultSet
     */
    public Optional<List<Optional<Computer>>> findAll() {
        connection = databaseManager.getConnection();
        ResultSet resultSet = null;
        List<Optional<Computer>> computers = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_COMPUTERS);
            while (resultSet.next()) {
                if (MapperComputer.resultSetToComputer(Optional.ofNullable(resultSet)).isPresent()) {
                    computers.add(MapperComputer.resultSetToComputer(Optional.ofNullable(resultSet)));
                }
            }

        } catch (SQLException e) {
            throw new PersistenceException(e);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            databaseManager.closeConnection();
        }
        return Optional.ofNullable(computers);
    }

    /**
     * Delete a computer on database.
     * @param id :
     */
    public void delete(long id) {

        connection = databaseManager.getConnection();
        try {
            PreparedStatement preparedStatementDelete = connection.prepareStatement(DELETE_COMPUTER_BY_ID);
            preparedStatementDelete.setLong(1, id);
            if (preparedStatementDelete.executeUpdate() == 0) {
                LOGGER.info("This id doesn't match any computer in database");
            }
            databaseManager.commit();
        } catch (SQLException e) {
            databaseManager.rollback();
            throw new PersistenceException(e);
        } finally {
            databaseManager.closeConnection();
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
            connection = databaseManager.getConnection();
            try {
                System.out.println(computer.getManufacturer() != null);
                PreparedStatement preparedStatementUpdate = connection.prepareStatement(UPDATE_COMPUTER_BY_ID);
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
                throw new PersistenceException(e);
            } finally {
                databaseManager.rollback();
                databaseManager.closeConnection();
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
            connection = databaseManager.getConnection();
            try {
                PreparedStatement preparedStatementInsert = connection.prepareStatement(INSERT_COMPUTER);
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
            } finally {
                databaseManager.closeConnection();
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
        connection = databaseManager.getConnection();
        Page page = new Page.Builder().build();
        try {
            PreparedStatement preparedStatementPagination = connection.prepareStatement(PAGINATION_COMPUTERS);
            preparedStatementPagination.setString(1, "%" + name + "%");
            preparedStatementPagination.setInt(2, size);
            preparedStatementPagination.setInt(3, offset);
            resultSet = preparedStatementPagination.executeQuery();
            while (resultSet.next()) {
                if (MapperComputer.resultSetToComputer(Optional.ofNullable(resultSet)).isPresent()) {
                    computers.add(MapperComputer.resultSetToComputer(Optional.ofNullable(resultSet)).get());
                }
            }

        } catch (SQLException e) {
            throw new PersistenceException(e);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            databaseManager.closeConnection();
        }
        return computers;
    }

    /* (non-Javadoc)
     * @see com.excilys.computerdatabase.dao.Crud#getNumber()
     */
    @Override
    public int getNumber() {
        connection = databaseManager.getConnection();
        ResultSet resultSet = null;
        int number = 0;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_COMPUTERS_NUMBER);
            resultSet.next();
            number = resultSet.getInt("number");
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            databaseManager.closeConnection();
        }
        return number;
        }

}
