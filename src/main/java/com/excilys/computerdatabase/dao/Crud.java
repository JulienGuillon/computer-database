package com.excilys.computerdatabase.dao;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

/**
 * @author Guillon Julien
 *
 * 20 févr. 2017
 * 
 * Contains definition of crud operation
 */
public interface Crud<T> {
	
	public Optional<T> find(long id);
	
	public Optional<ResultSet> findAll();
	
	public Optional<List<Optional<T>>> findUsingPagination(int offset);
	
}
