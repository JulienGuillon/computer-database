package com.excilys.computerdatabase.dao;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import com.excilys.computerdatabase.exception.PersistenceException;

/**
 * @author Guillon Julien
 *
 * 20 févr. 2017
 * 
 * Contains definition of crud operation
 */
public interface Crud<T> {
	
	public Optional<T> find(long id) throws PersistenceException;
	
	public Optional<ResultSet> findAll() throws PersistenceException;
	
	public Optional<List<Optional<T>>> findUsingPagination(int offset) throws PersistenceException;
	
	public Optional<List<Optional<T>>> findUsingPagination(int offset, int size) throws PersistenceException;
}
