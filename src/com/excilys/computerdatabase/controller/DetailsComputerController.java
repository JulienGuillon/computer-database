package com.excilys.computerdatabase.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerdatabase.dao.impl.CrudComputerImpl;
import com.excilys.computerdatabase.entities.Computer;
import com.excilys.computerdatabase.exception.PersistenceException;
import com.excilys.computerdatabase.view.DetailsComputerView;

/**
 * @author Guillon Julien
 *
 * 21 févr. 2017
 * 
 * Controller for the DetailsComputerView
 * It is a singleton.
 * Allows to catch event on view DetailsComputerView and make validation.
 * 
 */
public enum DetailsComputerController {
	INSTANCE;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DetailsComputerController.class);
		
	private DetailsComputerView detailsComputerView;
	
	private CrudComputerImpl crudComputer;

	private DetailsComputerController()
	{
		crudComputer = new CrudComputerImpl();
	}
	

	/**
	 * @param optionalDetailsComputerView
	 */
	public void setDetailsComputerView(Optional<DetailsComputerView> optionalDetailsComputerView) {
		if(optionalDetailsComputerView.isPresent()) {
			this.detailsComputerView = optionalDetailsComputerView.get();
		}
	}

	/**
	 * @param choice
	 * @throws PersistenceException 
	 */
	public void findComputerById(int choice) throws PersistenceException {
		Optional<Computer> computer = crudComputer.find(choice);
		if(computer.isPresent())
		{
			detailsComputerView.displayDetails(computer);
		}
	}

	/**
	 * @param operation
	 * @throws PersistenceException 
	 */
	public void makeOperation(Optional<String> optionalOperation, long id) throws PersistenceException {
		if(optionalOperation.isPresent()) {
			String operation = optionalOperation.get();
			switch (operation)
			{
				case "d":
					crudComputer.delete(id);
					detailsComputerView.displayDeletion(id);
					break;
				case "u":
					break;
				default:
					LOGGER.info("Choice is not valid !");
					break;
			}
		}
	}


	
	
}
