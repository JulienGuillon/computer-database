package com.excilys.computerdatabase.dao;

import java.sql.ResultSet;

/**
 * @author Guillon Julien
 *
 * 20 févr. 2017
 */
public interface ICrud<T> {
	
	public T find(int pId);
	
	public ResultSet findAll();
	
}
