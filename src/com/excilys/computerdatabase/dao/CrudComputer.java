package com.excilys.computerdatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.excilys.computerdatabase.entities.Computer;
import com.excilys.computerdatabase.interfaces.IComputer;

/**
 * @author Guillon Julien
 *
 * 20 févr. 2017
 * 
 * Allows to make all the crud operation on entity computer
 */
public class CrudComputer implements ICrud<IComputer>{

	
	private Connection connection = ManagerDatabase.getInstance().getConnection();
	private Statement statement;
	private ResultSet resultSet;
	private PreparedStatement preparedStatement;
	private PreparedStatement preparedStatementDelete;
	private PreparedStatement preparedStatementUpdate;
	private PreparedStatement preparedStatementInsert;
	private PreparedStatement preparedStatementPagination;
	
	public CrudComputer()
	{
		try {
			statement = connection.createStatement();
			preparedStatement = connection.prepareStatement(ConstanteQuery.SELECT_COMPUTER_BY_ID);
			preparedStatementDelete = connection.prepareStatement(ConstanteQuery.DELETE_COMPUTER_BY_ID);
			preparedStatementUpdate = connection.prepareStatement(ConstanteQuery.UPDATE_COMPUTER_BY_ID);
			preparedStatementInsert = connection.prepareStatement(ConstanteQuery.INSERT_COMPUTER);
			preparedStatementPagination = connection.prepareStatement(ConstanteQuery.PAGINATION_COMPUTERS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.dao.ICrud#find(java.lang.String, int)
	 */
	@Override
	public IComputer find(int pId) {
		IComputer computer = null;
		// TODO Auto-generated method stub
		try {
			preparedStatement.setInt(1, pId);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			computer = setToComputer(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return computer;
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.dao.ICrud#findAll(java.lang.String)
	 */
	@Override
	public ResultSet findAll() {
		try {
			resultSet = statement.executeQuery(ConstanteQuery.SELECT_COMPUTERS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}
	
	/**
	 * Delete a computer on database
	 * @param pId
	 */
	public void delete(int pId)
	{
		try {
			preparedStatementDelete.setInt(1, pId);
			preparedStatementDelete.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Update a computer on database
	 * @param pComputer
	 * @param pId
	 */
	public void update(IComputer pComputer, int pId)
	{
		try {
			preparedStatementUpdate.setString(1, pComputer.getName());
			preparedStatementUpdate.setObject(2, pComputer.getIntroduced());
			preparedStatementUpdate.setObject(3, pComputer.getDiscontinued());
			preparedStatementUpdate.setInt(4, pComputer.getManufacturer().getId());
			preparedStatementUpdate.setInt(5, pId);
			preparedStatementUpdate.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Persist a computer on database
	 * @param pComputer
	 */
	public void create(IComputer pComputer)
	{
		try {
			preparedStatementInsert.setString(1, pComputer.getName());
			preparedStatementInsert.setObject(2, pComputer.getIntroduced());
			preparedStatementInsert.setObject(3, pComputer.getDiscontinued());
			preparedStatementInsert.setInt(4, pComputer.getManufacturer().getId());
			preparedStatementInsert.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Find computers using pagination on database
	 * @param pOffset
	 * @return
	 */
	public List<IComputer> findUsingPagination(int pOffset)
	{
		List<IComputer> computers = new ArrayList<>();
		try {
			preparedStatementPagination.setInt(1,  ConstanteQuery.PAGE);
			preparedStatementPagination.setInt(2, pOffset);
			resultSet = preparedStatementPagination.executeQuery();
			while(resultSet.next())
			{
				computers.add(setToComputer(resultSet));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return computers;
	}

	/**
 	 * Allows to get a computer from a ResultSet
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public IComputer setToComputer(ResultSet resultSet) throws SQLException, Exception
	{
		IComputer computer = new Computer.ComputerBuilder(resultSet.getString("name")).build();
		computer.setId(resultSet.getInt("id"));
		computer.setIntroduced((Date) resultSet.getObject("introduced"));
		computer.setDiscontinued((Date) resultSet.getObject("introduced"));
		int idCompany = resultSet.getInt("company_id");
		if(idCompany != 0)
			computer.setManufacturer(new CrudCompany().find(idCompany));
		return computer;
	}
}
