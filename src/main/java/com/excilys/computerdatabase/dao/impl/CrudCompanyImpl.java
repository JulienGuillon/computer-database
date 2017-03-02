package com.excilys.computerdatabase.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerdatabase.dao.DatabaseManager;
import com.excilys.computerdatabase.dao.CrudCompany;
import com.excilys.computerdatabase.dao.mapper.MapperCompany;
import com.excilys.computerdatabase.entities.Company;
import com.excilys.computerdatabase.exceptions.PersistenceException;
import com.excilys.computerdatabase.utils.PageCompany;

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

    private static final String SELECT_COMPANIES = "select * from company;";
    private static final String SELECT_COMPANY_BY_ID = "select * from company where id= ?;";
    private static final String PAGINATION_COMPANIES = "select * from company limit ? offset ?;";
    private static final String SELECT_COMPANIES_NUMBER = "select count(*) as number from company;";

    private DatabaseManager databaseManager = DatabaseManager.INSTANCE;
    private Connection connection;
    private ResultSet resultSet;

    /**
     * @param id :
     * @return an Optional Company
     */
    public Optional<Company> find(long id) {
        Optional<Company> company = null;
        connection = databaseManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COMPANY_BY_ID);
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
            resultSet = statement.executeQuery(SELECT_COMPANIES);
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
     * Find companies using pagination from database.
     *
     * @return an Optional list of companies
     */
    public Optional<List<Optional<Company>>> findUsingPagination() {
        connection = databaseManager.getConnection();
        List<Optional<Company>> companies = new ArrayList<>();
        try {
            PreparedStatement preparedStatementPagination = connection.prepareStatement(PAGINATION_COMPANIES);
            preparedStatementPagination.setInt(1, PageCompany.getSize());
            preparedStatementPagination.setInt(2, PageCompany.getOffset());
            resultSet = preparedStatementPagination.executeQuery();
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
        return Optional.of(companies);
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
            resultSet = statement.executeQuery(SELECT_COMPANIES_NUMBER);
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
