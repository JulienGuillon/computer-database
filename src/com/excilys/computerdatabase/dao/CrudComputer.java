package com.excilys.computerdatabase.dao;

import com.excilys.computerdatabase.entities.Computer;

/**
 * @author Guillon Julien
 *
 * 24 févr. 2017
 */
public interface CrudComputer extends Crud<Computer> {
	public void create(Computer computer);
	
	public void delete(long id);

	public void update(Computer computer, long id);

}
