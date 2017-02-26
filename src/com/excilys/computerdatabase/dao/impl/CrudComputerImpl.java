package com.excilys.computerdatabase.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerdatabase.dao.DatabaseManager;
import com.excilys.computerdatabase.dao.CrudComputer;
import com.excilys.computerdatabase.dao.mapper.MapperComputer;
import com.excilys.computerdatabase.entities.Computer;
import com.excilys.computerdatabase.exception.PersistenceException;

/**
 * @author Guillon Julien
 *
 * 20 févr. 2017
 * 
 * Allows to make all the crud operation on entity computer
 */

public class CrudComputerImpl implements CrudComputer {

	private static final Logger LOGGER = LoggerFactory.getLogger(CrudComputerImpl.class);
	
	private static int PAGE = 10;
	private static final String SELECT_COMPUTERS = "select computer.id, computer.name, introduced, discontinued, company_id, company.name company_name from computer left join company on company.id = computer.company_id;";
	private static final String SELECT_COMPUTER_BY_ID = "select computer.id, computer.name, introduced, discontinued, company_id, company.name company_name from computer left join company on company.id = computer.company_id where computer.id= ?;";
	private static final String PAGINATION_COMPUTERS = "select computer.id, computer.name, introduced, discontinued, company_id, company.name company_name from computer left join company on company.id = computer.company_id limit ? offset ?;";
	private static final String DELETE_COMPUTER_BY_ID = "delete from computer where id = ? ";
	private static final String UPDATE_COMPUTER_BY_ID = "update computer set name = ?, introduced = ?, discontinued = ?, company_id = ? where id = ?";
	private static final String INSERT_COMPUTER = "insert into computer(name, introduced, discontinued, company_id) values (?, ?, ?, ?)";
	
	private DatabaseManager databaseManager = DatabaseManager.INSTANCE;
	private Connection connection;
	private ResultSet resultSet;
	
	public CrudComputerImpl()
	{

	}
	
	
	
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.dao.ICrud#find(java.lang.String, int)
	 */
	public Optional<Computer> find(long id) throws PersistenceException {
		Optional<Computer> computer = Optional.empty();
		connection = databaseManager.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COMPUTER_BY_ID);
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				computer = MapperComputer.resultSetToComputer(Optional.ofNullable(resultSet));
			}
			else
			{
				LOGGER.info("Id doesn't match any computer in database");
			}
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally
		{
			databaseManager.closeConnection();
		}
		return computer;
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.dao.ICrud#findAll(java.lang.String)
	 */
	public Optional<ResultSet> findAll() throws PersistenceException {
		connection = databaseManager.getConnection();
		ResultSet resultSet = null;
		try {
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery(SELECT_COMPUTERS);
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally
		{
			databaseManager.closeConnection();
		}
		return Optional.ofNullable(resultSet);
	}
	
	/**
	 * Delete a computer on database
	 * @param id
	 * @throws PersistenceException 
	 */
	public void delete(long id) throws PersistenceException
	{
		
		connection = databaseManager.getConnection();
		try {
			PreparedStatement preparedStatementDelete = connection.prepareStatement(DELETE_COMPUTER_BY_ID);
			preparedStatementDelete.setLong(1, id);
			if(preparedStatementDelete.executeUpdate() == 0)
			{
				LOGGER.info("This id doesn't match any computer in database");
			}
			databaseManager.commit();
		} catch (SQLException e) {
			databaseManager.rollback();
			throw new PersistenceException(e);
		} finally
		{
			databaseManager.closeConnection();
		}
	}
	
	/**
	 * Update a computer on database
	 * @param computer
	 * @param id
	 * @throws PersistenceException 
	 */
	public void update(Optional<Computer> optionalComputer, long id) throws PersistenceException
	{
		if(optionalComputer.isPresent())
		{
			Computer computer = optionalComputer.get();
			connection = databaseManager.getConnection();
			try {
				System.out.println(computer.getManufacturer() != null);
				PreparedStatement preparedStatementUpdate = connection.prepareStatement(UPDATE_COMPUTER_BY_ID);
				preparedStatementUpdate.setString(1, computer.getName());
				preparedStatementUpdate.setDate(2, computer.getIntroduced() != null ? Date.valueOf(computer.getIntroduced()) : null);
				preparedStatementUpdate.setDate(3, computer.getDiscontinued() != null ? Date.valueOf(computer.getDiscontinued()) : null);
				if(computer.getManufacturer() != null) {
					preparedStatementUpdate.setLong(4, computer.getManufacturer().getId());
				} else {
					preparedStatementUpdate.setNull(4,Types.BIGINT);
				}
				preparedStatementUpdate.setLong(5, id);
				preparedStatementUpdate.execute();
				databaseManager.commit();
			} catch (SQLException e) {
				throw new PersistenceException(e);
			} finally
			{
				databaseManager.rollback();
				databaseManager.closeConnection();
			}
		}
	}
	
	/**
	 * Persist a computer on database
	 * @param computer
	 * @throws PersistenceException 
	 */
	public void create(Optional<Computer> optionalComputer) throws PersistenceException
	{
		if(optionalComputer.isPresent())
		{
			Computer computer = optionalComputer.get();
			connection = databaseManager.getConnection();
			try
			{
				PreparedStatement preparedStatementInsert = connection.prepareStatement(INSERT_COMPUTER);
				preparedStatementInsert.setString(1, computer.getName());
				preparedStatementInsert.setObject(2, computer.getIntroduced());
				preparedStatementInsert.setObject(3, computer.getDiscontinued());
				preparedStatementInsert.setLong(4, computer.getManufacturer().getId());
				preparedStatementInsert.execute();
				databaseManager.commit();
			} catch (SQLException e)
			{
				databaseManager.rollback();
				throw new PersistenceException(e);
			} finally
			{
				databaseManager.closeConnection();
			}
		}
	}
	
	/**
	 * Find computers using pagination on database
	 * @param offset
	 * @return
	 * @throws PersistenceException 
	 */
	public Optional<List<Optional<Computer>>> findUsingPagination(int offset) throws PersistenceException
	{
		List<Optional<Computer>> computers = new ArrayList<>();
		connection = databaseManager.getConnection();
		try
		{
			PreparedStatement preparedStatementPagination = connection.prepareStatement(PAGINATION_COMPUTERS);
			preparedStatementPagination.setInt(1,  PAGE);
			preparedStatementPagination.setInt(2, offset);
			resultSet = preparedStatementPagination.executeQuery();
			while(resultSet.next())
			{
				if(MapperComputer.resultSetToComputer(Optional.ofNullable(resultSet)).isPresent())
				{
					computers.add(MapperComputer.resultSetToComputer(Optional.ofNullable(resultSet)));
				}
			}
			
		} catch (SQLException e)
		{
			throw new PersistenceException(e);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			databaseManager.closeConnection();
		}
		return Optional.of(computers);
	}



	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.dao.Crud#findUsingPagination(int, int)
	 */
	@Override
	public Optional<List<Optional<Computer>>> findUsingPagination(int offset, int size) throws PersistenceException {
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
