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
import com.excilys.computerdatabase.utils.Page;

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
    
    private Properties properties = loadProperties.getProperties();

    private static final String SELECT_COMPANIES = "SELECT_COMPANIES";
    private static final String SELECT_COMPANY_BY_ID = "select * from company where id= ?;";
    private static final String PAGINATION_COMPANIES = "select * from company limit ? offset ?;";
    private static final String SELECT_COMPANIES_NUMBER = "select count(*) as number from company;";

    private DatabaseManager databaseManager = DatabaseManager.INSTANCE;
    private Connection connection;
    private ResultSet resultSet;
    private ServiceCompany serviceCompany = ServiceCompany.INSTANCE;

    private CrudCompanyImpl() {
        loadProperties.setFileName("queries.properties");
        loadProperties.initLoadProperties();
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
    public Optional<List<Optional<Company>>> findAll() {
        connection = databaseManager.getConnection();
        List<Optional<Company>> companies = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(properties.getProperty("SELECT_COMPANIES"));
            while (resultSet.next()) {
                if (MapperCompany.resultSetToCompany(Optional.ofNullable(resultSet)).isPresent()) {
                    companies.add(MapperCompany.resultSetToCompany(Optional.ofNullable(resultSet)));
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
        return Optional.ofNullable(companies);
    }
    
    /**
     * Find companies using pagination on database.
     *
     * @return an Optional list of company
     */
    public List<Company> findUsingPagination(int size, int offset, String name) {
        List<Company> companies = new ArrayList<>();
        connection = databaseManager.getConnection();
        Page page = new Page.Builder().build();
        try {
            PreparedStatement preparedStatementPagination = connection.prepareStatement(properties.getProperty("PAGINATION_COMPANIES"));
            preparedStatementPagination.setString(1, "%" + name + "%");
            preparedStatementPagination.setInt(2, size);
            preparedStatementPagination.setInt(3, offset);
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
    public int getNumber() {
        connection = databaseManager.getConnection();
        ResultSet resultSet = null;
        int number = 0;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("select count(*) as number from company;");
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
