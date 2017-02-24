package com.excilys.computerdatabase.controller;

import java.util.Optional;

import com.excilys.computerdatabase.dao.impl.CrudComputerImpl;
import com.excilys.computerdatabase.entities.Computer;
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
	 * @param pUpdateComputerView
	 */
	public void setUpdateComputerView(UpdateComputerView pUpdateComputerView) {
		this.updateComputerView = pUpdateComputerView;
	}

	/**
	 * Find a computer by id by using crud method
	 * and call to update view to display details of computer
	 * @param choice
	 * @throws Exception 
	 */
	public void findComputerById(int choice) {
		Optional<Computer> computer = crudComputer.find(choice);
		if(computer.isPresent())
		{
			updateComputerView.displayDetailsToUpdate(computer.get());		
		}
	}

	/**
	 * Update a computer by using crud method
	 * and call to update view to display MainMenu 
	 * @param computer
	 * @throws Exception 
	 */
	public void update(Computer computer, long id) {
		crudComputer.update(computer, id);
		updateComputerView.displayInfoComputer(computer);
	}

}
