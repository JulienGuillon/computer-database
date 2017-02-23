package com.excilys.computerdatabase.controller;

import java.util.Optional;

import com.excilys.computerdatabase.dao.impl.CrudComputer;
import com.excilys.computerdatabase.entities.Computer;
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
		
	private DetailsComputerView detailsComputerView;
	
	private CrudComputer crudComputer;

	private DetailsComputerController()
	{
		crudComputer = new CrudComputer();
	}
	

	/**
	 * @param detailsComputerView
	 */
	public void setDetailsComputerView(DetailsComputerView detailsComputerView) {
		this.detailsComputerView = detailsComputerView;
	}

	/**
	 * @param choice
	 */
	public void findComputerById(int choice) {
		Optional<Computer> computer = crudComputer.find(choice);
		if(computer.isPresent())
		{
			detailsComputerView.displayDetails(computer.get());
		}
	}

	/**
	 * @param operation
	 */
	public void makeOperation(String operation, long id) {
		switch (operation)
		{
			case "d":
				crudComputer.delete(id);
				detailsComputerView.displayDeletion(id);
				break;
			case "u":
				break;
			default:
				break;
		}
	}


	
	
}
