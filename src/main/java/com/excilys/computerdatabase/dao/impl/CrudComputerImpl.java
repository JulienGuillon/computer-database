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

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerdatabase.dao.DatabaseManager;
import com.excilys.computerdatabase.dao.CrudComputer;
import com.excilys.computerdatabase.dao.mapper.MapperComputer;
import com.excilys.computerdatabase.entities.Computer;
import com.excilys.computerdatabase.exception.PersistenceException;

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

    private static int page = 10;
    private String name = "";
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
    public Optional<ResultSet> findAll() {
        connection = databaseManager.getConnection();
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_COMPUTERS);
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            databaseManager.closeConnection();
        }
        return Optional.ofNullable(resultSet);
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
     * @param id :
     *
     */
    public void update(Optional<Computer> optionalComputer, long id) {
        if (optionalComputer.isPresent()) {
            Computer computer = optionalComputer.get();
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
                preparedStatementInsert.setLong(4, computer.getManufacturer().getId());
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
     * @param offset :
     * @return an Optional list of computer
     */
    public Optional<List<Optional<Computer>>> findUsingPagination(int offset) {
        List<Optional<Computer>> computers = new ArrayList<>();
        connection = databaseManager.getConnection();
        try {
            PreparedStatement preparedStatementPagination = connection.prepareStatement(PAGINATION_COMPUTERS);
            preparedStatementPagination.setString(1, "%"+name+"%");
            preparedStatementPagination.setInt(2, page);
            preparedStatementPagination.setInt(3, offset);
            resultSet = preparedStatementPagination.executeQuery();
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
            ResultSet resultSet = null;
        }
        return Optional.of(computers);
    }


    @Override
    public Optional<List<Optional<Computer>>> findUsingPagination(int offset, int size) {
        if (size <= 10) {
            LOGGER.info("Size of page is not valid, default size is used !");
            page = 10;
        } else {
            page = size;
        }
        return findUsingPagination(offset);
    }
    
    @Override
    public Optional<List<Optional<Computer>>> findUsingPaginationFilterByName(int offset, int size, String name) {
        if (StringUtils.isBlank(name)) {
            LOGGER.info("String for filter is not valid, default filter is used !");
            this.name = "";
        } else {
            this.name = name;
        }
        return findUsingPagination(offset);
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
