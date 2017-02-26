package com.excilys.computerdatabase.dao;

import java.util.Optional;

import com.excilys.computerdatabase.entities.Computer;
import com.excilys.computerdatabase.exception.PersistenceException;

/**
 * @author Guillon Julien
 *
 * 24 févr. 2017
 */
public interface CrudComputer extends Crud<Computer> {
	public void create(Optional<Computer> computer) throws PersistenceException;
	
	public void delete(long id) throws PersistenceException;

	public void update(Optional<Computer> computer, long id) throws PersistenceException;

}
