package com.excilys.computerdatabase.controller;

import java.util.Optional;

import com.excilys.computerdatabase.dao.impl.CrudComputerImpl;
import com.excilys.computerdatabase.entities.Computer;
import com.excilys.computerdatabase.exception.PersistenceException;
import com.excilys.computerdatabase.view.UpdateComputerView;

/**
 * @author Guillon Julien
 *
 * 21 févr. 2017
 * 
 * Controler for the UpdateComputerView
 * It is a singleton.
 * Allows to catch event on view UpdateComputerView and make validation.
 *
 */
public enum UpdateComputerController {
	INSTANCE;
		
	private UpdateComputerView updateComputerView;
	
	private CrudComputerImpl crudComputer;
	
	private UpdateComputerController()
	{
		crudComputer = new CrudComputerImpl();
	}

	/**
	 * @param optionalUpdateComputerView
	 */
	public void setUpdateComputerView(Optional<UpdateComputerView> optionalUpdateComputerView) {
		if (optionalUpdateComputerView.isPresent()) {
			this.updateComputerView = optionalUpdateComputerView.get();
		}
	}

	/**
	 * Find a computer by id by using crud method
	 * and call to update view to display details of computer
	 * @param choice
	 * @throws PersistenceException 
	 * @throws Exception 
	 */
	public void findComputerById(int choice) throws PersistenceException {
		Optional<Computer> computer = crudComputer.find(choice);
		if(computer.isPresent())
		{
			updateComputerView.displayDetailsToUpdate(computer);		
		}
	}

	/**
	 * Update a computer by using crud method
	 * and call to update view to display MainMenu 
	 * @param computer
	 * @throws PersistenceException 
	 * @throws Exception 
	 */
	public void update(Optional<Computer> optionalComputer) throws PersistenceException {
		if (optionalComputer.isPresent()) {
			Computer computer = optionalComputer.get();
			crudComputer.update(Optional.ofNullable(computer), computer.getId());
			updateComputerView.displayInfoComputer(Optional.ofNullable(computer));
		}
	}

}
