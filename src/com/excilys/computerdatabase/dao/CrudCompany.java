package com.excilys.computerdatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.dao.mapper.MapperCompany;
import com.excilys.computerdatabase.entities.Company;

/**
 * @author Guillon Julien
 *
 * 20 févr. 2017
 * 
 * Allows to make all the crud operation on entity company
 */
public class CrudCompany implements ICrud<Company>{
	
	private static final int PAGE = 10;
	private static final String SELECT_COMPANIES = "select * from company;";
	private static final String SELECT_COMPANY_BY_ID = "select * from company where id= ?;";
	private static final String PAGINATION_COMPANIES = "select * from company limit ? offset ?;";
	
	private Connection connection = DatabaseManager.INSTANCE.getConnection();
	private Statement statement;
	private ResultSet resultSet;
	private PreparedStatement preparedStatement;
	private PreparedStatement preparedStatementPagination;

	
	public CrudCompany()
	{
		try {
			statement = connection.createStatement();
			preparedStatement = connection.prepareStatement(SELECT_COMPANY_BY_ID);
			preparedStatementPagination = connection.prepareStatement(PAGINATION_COMPANIES);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.dao.ICrud#find(java.lang.String, int)
	 */
	@Override
	public Company find(int id) {
		Company company = null;
		// TODO Auto-generated method stub
		try {
			preparedStatement.setString(1, String.valueOf(id));
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			company = MapperCompany.setToCompany(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return company;
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.dao.ICrud#findAll(java.lang.String)
	 */
	@Override
	public ResultSet findAll() {
		try {
			resultSet = statement.executeQuery(SELECT_COMPANIES);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}
	
	
	/**
	 * Find companies using pagination from database
	 * @param offset
	 * @return
	 */
	public List<Company> findUsingPagination(int offset)
	{
		List<Company> companies = new ArrayList<>();
		try {
			preparedStatementPagination.setInt(1,  PAGE);
			preparedStatementPagination.setInt(2, offset);
			resultSet = preparedStatementPagination.executeQuery();
			while(resultSet.next())
			{
				companies.add(MapperCompany.setToCompany(resultSet));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return companies;
	}
	
}
