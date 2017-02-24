package com.excilys.computerdatabase.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.computerdatabase.dao.DatabaseManager;
import com.excilys.computerdatabase.dao.CrudCompany;
import com.excilys.computerdatabase.dao.mapper.MapperCompany;
import com.excilys.computerdatabase.entities.Company;

/**
 * @author Guillon Julien
 *
 * 20 févr. 2017
 * 
 * Allows to make all the crud operation on entity company
 */
public class CrudCompanyImpl implements CrudCompany {
	
	private static final int PAGE = 10;
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
	public Optional<Company> find(long id) {
		Optional<Company> company = null;
		connection = databaseManager.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COMPANY_BY_ID);
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			company = MapperCompany.resultSetToCompany(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public Optional<ResultSet> findAll() {
		connection = databaseManager.getConnection();
		try {
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery(SELECT_COMPANIES);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			databaseManager.closeConnection();
		}
		return Optional.of(resultSet);
	}
	
	
	/**
	 * Find companies using pagination from database
	 * @param offset
	 * @return
	 */
	public Optional<List<Optional<Company>>> findUsingPagination(int offset)
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
				if(MapperCompany.resultSetToCompany(resultSet).isPresent())
				{
					companies.add(MapperCompany.resultSetToCompany(resultSet));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			databaseManager.closeConnection();
		}
		return Optional.of(companies);
	}
	
}