package com.excilys.computerdatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.entities.Company;
import com.excilys.computerdatabase.interfaces.ICompany;

/**
 * @author Guillon Julien
 *
 * 20 févr. 2017
 * 
 * Allows to make all the crud operation on entity company
 */
public class CrudCompany implements ICrud<ICompany>{

	
	private Connection connection = ManagerDatabase.getInstance().getConnection();
	private Statement statement;
	private ResultSet resultSet;
	private PreparedStatement preparedStatement;
	private PreparedStatement preparedStatementInsert;
	private PreparedStatement preparedStatementPagination;

	
	public CrudCompany()
	{
		try {
			statement = connection.createStatement();
			preparedStatement = connection.prepareStatement(ConstanteQuery.SELECT_COMPANY_BY_ID);
			preparedStatementInsert = connection.prepareStatement(ConstanteQuery.INSERT_COMPANY);
			preparedStatementPagination = connection.prepareStatement(ConstanteQuery.PAGINATION_COMPANIES);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.dao.ICrud#find(java.lang.String, int)
	 */
	@Override
	public ICompany find(int pId) {
		ICompany company = null;
		// TODO Auto-generated method stub
		try {
			preparedStatement.setString(1, String.valueOf(pId));
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			company = setToCompany(resultSet);
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
			resultSet = statement.executeQuery(ConstanteQuery.SELECT_COMPANIES);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}
	
	/**
	 * Persist a company on database
	 * @param pCompany
	 */
	public void create(ICompany pCompany)
	{
		try {
			preparedStatementInsert.setString(1, pCompany.getName());
			preparedStatementInsert.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Find companies using pagination from database
	 * @param pOffset
	 * @return
	 */
	public List<ICompany> findUsingPagination(int pOffset)
	{
		List<ICompany> companies = new ArrayList<>();
		try {
			preparedStatementPagination.setInt(1,  ConstanteQuery.PAGE);
			preparedStatementPagination.setInt(2, pOffset);
			resultSet = preparedStatementPagination.executeQuery();
			while(resultSet.next())
			{
				companies.add(setToCompany(resultSet));
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
	
	/**
	 * Allows to get a company from a ResultSet 
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public ICompany setToCompany(ResultSet resultSet) throws SQLException, Exception
	{
		ICompany company = new Company.CompanyBuilder(resultSet.getString("name")).build();
		company.setId(resultSet.getInt("id"));
		return company;
	}

}
