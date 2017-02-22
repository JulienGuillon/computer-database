package com.excilys.computerdatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.dao.mapper.MapperComputer;
import com.excilys.computerdatabase.entities.Computer;

/**
 * @author Guillon Julien
 *
 * 20 févr. 2017
 * 
 * Allows to make all the crud operation on entity computer
 */
public class CrudComputer implements ICrud<Computer>{

	private static final int PAGE = 10;
	private static final String SELECT_COMPUTERS = "select computer.id, computer.name, introduced, discontinued, company_id, company.name cname from computer left join company on company.id = computer.company_id;";
	private static final String SELECT_COMPUTER_BY_ID = "select computer.id, computer.name, introduced, discontinued, company_id, company.name cname from computer left join company on company.id = computer.company_id where computer.id= ?;";
	private static final String PAGINATION_COMPUTERS = "select computer.id, computer.name, introduced, discontinued, company_id, company.name cname from computer left join company on company.id = computer.company_id limit ? offset ?;";
	private static final String DELETE_COMPUTER_BY_ID = "delete from computer where id = ? ";
	private static final String UPDATE_COMPUTER_BY_ID = "update computer set name = ?, introduced = ?, discontinued = ?, company_id = ? where id = ?";
	private static final String INSERT_COMPUTER = "insert into computer(name, introduced, discontinued, company_id) values (?, ?, ?, ?)";
	
	private Connection connection = DatabaseManager.INSTANCE.getConnection();
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
			preparedStatement = connection.prepareStatement(SELECT_COMPUTER_BY_ID);
			preparedStatementDelete = connection.prepareStatement(DELETE_COMPUTER_BY_ID);
			preparedStatementUpdate = connection.prepareStatement(UPDATE_COMPUTER_BY_ID);
			preparedStatementInsert = connection.prepareStatement(INSERT_COMPUTER);
			preparedStatementPagination = connection.prepareStatement(PAGINATION_COMPUTERS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.dao.ICrud#find(java.lang.String, int)
	 */
	@Override
	public Computer find(int id) {
		Computer computer = null;
		// TODO Auto-generated method stub
		try {
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			computer = MapperComputer.setToComputer(resultSet);
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
			resultSet = statement.executeQuery(SELECT_COMPUTERS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}
	
	/**
	 * Delete a computer on database
	 * @param id
	 */
	public void delete(long id)
	{
		try {
			preparedStatementDelete.setLong(1, id);
			preparedStatementDelete.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Update a computer on database
	 * @param computer
	 * @param id
	 */
	public void update(Computer computer, long id)
	{
		try {
			preparedStatementUpdate.setString(1, computer.getName());
			preparedStatementUpdate.setObject(2, computer.getIntroduced());
			preparedStatementUpdate.setObject(3, computer.getDiscontinued());
			preparedStatementUpdate.setLong(4, computer.getManufacturer().getId());
			preparedStatementUpdate.setLong(5, id);
			preparedStatementUpdate.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Persist a computer on database
	 * @param computer
	 */
	public void create(Computer computer)
	{
		try {
			preparedStatementInsert.setString(1, computer.getName());
			preparedStatementInsert.setObject(2, computer.getIntroduced());
			preparedStatementInsert.setObject(3, computer.getDiscontinued());
			preparedStatementInsert.setLong(4, computer.getManufacturer().getId());
			preparedStatementInsert.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Find computers using pagination on database
	 * @param offset
	 * @return
	 */
	public List<Computer> findUsingPagination(int offset)
	{
		List<Computer> computers = new ArrayList<>();
		try {
			preparedStatementPagination.setInt(1,  PAGE);
			preparedStatementPagination.setInt(2, offset);
			resultSet = preparedStatementPagination.executeQuery();
			while(resultSet.next())
			{
				computers.add(MapperComputer.setToComputer(resultSet));
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

}
