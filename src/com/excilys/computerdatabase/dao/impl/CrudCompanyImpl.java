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
import com.excilys.computerdatabase.dao.mapper.MapperComputer;
import com.excilys.computerdatabase.entities.Company;
import com.excilys.computerdatabase.exception.PersistenceException;

/**
 * @author Guillon Julien
 *
 * 20 févr. 2017
 * 
 * Allows to make all the crud operation on entity company
 */
public class CrudCompanyImpl implements CrudCompany {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrudCompanyImpl.class);
			
	private static int PAGE = 10;
	private static final String SELECT_COMPANIES = "select * from company;";
	private static final String SELECT_COMPANY_BY_ID = "select * from company where id= ?;";
	private static final String PAGINATION_COMPANIES = "select * from company limit ? offset ?;";

	private DatabaseManager databaseManager = DatabaseManager.INSTANCE;
	private Connection connection;
	private ResultSet resultSet;

	
	public CrudCompanyImpl()
	{
	}
	
	
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.dao.ICrud#find(java.lang.String, int)
	 */
	public Optional<Company> find(long id) throws PersistenceException {
		Optional<Company> company = null;
		connection = databaseManager.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COMPANY_BY_ID);
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				company = MapperCompany.resultSetToCompany(Optional.ofNullable(resultSet));
			}
			else
			{
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

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.dao.ICrud#findAll(java.lang.String)
	 */
	public Optional<ResultSet> findAll() throws PersistenceException {
		connection = databaseManager.getConnection();
		try {
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery(SELECT_COMPANIES);
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			databaseManager.closeConnection();
		}
		return Optional.of(resultSet);
	}
	
	
	/**
	 * Find companies using pagination from database
	 * @param offset
	 * @return
	 * @throws PersistenceException 
	 */
	public Optional<List<Optional<Company>>> findUsingPagination(int offset) throws PersistenceException
	{
		connection = databaseManager.getConnection();
		List<Optional<Company>> companies = new ArrayList<>();
		try {
			PreparedStatement preparedStatementPagination = connection.prepareStatement(PAGINATION_COMPANIES);
			preparedStatementPagination.setInt(1,  PAGE);
			preparedStatementPagination.setInt(2, offset);
			resultSet = preparedStatementPagination.executeQuery();
			while(resultSet.next())
			{
				if(MapperCompany.resultSetToCompany(Optional.ofNullable(resultSet)).isPresent())
				{
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
	 * @see com.excilys.computerdatabase.dao.Crud#findUsingPagination(int, int)
	 */
	@Override
	public Optional<List<Optional<Company>>> findUsingPagination(int offset, int size) throws PersistenceException {
		if(size <= 10)
		{
			LOGGER.info("Size of page is not valid, default size is used !");
			PAGE = 10;
		}
		else {
			PAGE = size;
		}
		return findUsingPagination(offset);
	}
	
}
