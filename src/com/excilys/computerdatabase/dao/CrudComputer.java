package com.excilys.computerdatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.excilys.computerdatabase.entities.Computer;
import com.excilys.computerdatabase.interfaces.IComputer;

/**
 * @author Guillon Julien
 *
 * 20 févr. 2017
 */
public class CrudComputer implements ICrud<IComputer>{

	
	private Connection connection = ManagerDatabase.getInstance().getConnection();
	private Statement statement;
	private ResultSet resultSet;
	private PreparedStatement preparedStatement;
	private PreparedStatement preparedStatementDelete;
	private PreparedStatement preparedStatementUpdate;
	private PreparedStatement preparedStatementInsert;


	public CrudComputer()
	{
		// requete pour obtenir les infos necessaires à computer.
		// select * from computer join company on company.id = computer.company_id
		try {
			statement = connection.createStatement();
			preparedStatement = connection.prepareStatement(ConstanteQuery.SELECT_COMPUTER_BY_ID);
			preparedStatementDelete = connection.prepareStatement(ConstanteQuery.DELETE_COMPUTER_BY_ID);
			preparedStatementUpdate = connection.prepareStatement("delete from computer where id = ? ");
			preparedStatementInsert = connection.prepareStatement("insert into computervalues(name, introduced, discontinued, company_id);");
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
			preparedStatement.setInt(0, pId);
			resultSet.next();
			computer = new Computer.ComputerBuilder(resultSet.getString("name")).build();
			computer.setId(resultSet.getInt("id"));
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
	
	public void update(int pId)
	{
		try {
			preparedStatement.setInt(1, pId);
			preparedStatementDelete.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
