package com.excilys.computerdatabase.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerdatabase.dao.DatabaseManager;
import com.excilys.computerdatabase.dao.CrudCompany;
import com.excilys.computerdatabase.dao.mapper.MapperCompany;

import com.excilys.computerdatabase.entities.Company;
import com.excilys.computerdatabase.exceptions.PersistenceException;
import com.excilys.computerdatabase.services.ServiceCompany;
import com.excilys.computerdatabase.utils.LoadProperties;

/**
 * @author Guillon Julien
 *
 *         20 févr. 2017
 *
 *         Allows to make all the crud operation on entity company
 */
public enum CrudCompanyImpl implements CrudCompany {
    INSTANCE;
    private static final Logger LOGGER = LoggerFactory.getLogger(CrudCompanyImpl.class);

    private LoadProperties loadProperties = LoadProperties.INSTANCE;

    private Properties properties;

    private static final String SELECT_COMPANIES = "SELECT_COMPANIES";
    private static final String SELECT_COMPANY_BY_ID = "SELECT_COMPANY_BY_ID";
    private static final String PAGINATION_COMPANIES = "PAGINATION_COMPANIES";
    private static final String SELECT_COMPANIES_NUMBER = "SELECT_COMPANIES_NUMBER";

    private DatabaseManager databaseManager = DatabaseManager.INSTANCE;
    private Connection connection;
    private ResultSet resultSet;
    private ServiceCompany serviceCompany = ServiceCompany.INSTANCE;

    CrudCompanyImpl() {
        loadProperties.setFileName("queries.properties");
        loadProperties.initLoadProperties();
        properties = loadProperties.getProperties();
    }

    /**
     * @param id :
     * @return an Optional Company
     */
    public Optional<Company> find(long id) {
        Optional<Company> company = null;
        connection = databaseManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(properties.getProperty("SELECT_COMPANY_BY_ID"));
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                company = MapperCompany.resultSetToCompany(Optional.ofNullable(resultSet));
            } else {
                LOGGER.info("Id doesn't match any company in database");
            }
            resultSet.next();
            company = MapperCompany.resultSetToCompany(Optional.ofNullable(resultSet));
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            databaseManager.closeConnection();
        }
        return company;
    }

    /**
     * @return an Optional ResultSet
     */
    public List<Company> findAll() {
        connection = databaseManager.getConnection();
        List<Company> companies = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(properties.getProperty("SELECT_COMPANIES"));
            while (resultSet.next()) {
                if (MapperCompany.resultSetToCompany(Optional.ofNullable(resultSet)).isPresent()) {
                    companies.add(MapperCompany.resultSetToCompany(Optional.ofNullable(resultSet)).get());
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
        return companies;
    }

    /**
     * Find companies using pagination on database.
     *
     * @return an Optional list of company
     */
    public List<Company> findUsingPagination(int size, int offset, String name) {
        List<Company> companies = new ArrayList<>();
        connection = databaseManager.getConnection();
        try {
            PreparedStatement preparedStatementPagination = connection.prepareStatement(properties.getProperty("PAGINATION_COMPANIES"));
            //preparedStatementPagination.setString(1, "%" + name + "%");
            preparedStatementPagination.setInt(1, size);
            preparedStatementPagination.setInt(2, offset);
            resultSet = preparedStatementPagination.executeQuery();
            while (resultSet.next()) {
                if (MapperCompany.resultSetToCompany(Optional.ofNullable(resultSet)).isPresent()) {
                    companies.add(MapperCompany.resultSetToCompany(Optional.ofNullable(resultSet)).get());
                }
            }

        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            databaseManager.closeConnection();
        }
        return companies;
    }


    /* (non-Javadoc)
     * @see com.excilys.computerdatabase.dao.Crud#getNumber()
     */
    @Override
    public int getNumber(String filter) {
        connection = databaseManager.getConnection();
        ResultSet resultSet = null;
        int number = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(properties.getProperty(SELECT_COMPANIES_NUMBER));
            statement.setString(1, "%" + filter + "%");
            resultSet = statement.executeQuery();
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
