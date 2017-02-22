package com.excilys.computerdatabase.dao;

import java.sql.ResultSet;
import java.util.List;

/**
 * @author Guillon Julien
 *
 * 20 févr. 2017
 * 
 * Contains definition of crud operation
 */
public interface ICrud<T> {
	
	public T find(int pId);
	
	public ResultSet findAll();
	
	public List<T> findUsingPagination(int pOffset);
	
}
