package com.excilys.computerdatabase.controller;

import com.excilys.computerdatabase.dao.CrudComputer;
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
public class UpdateComputerController {

	private static final UpdateComputerController UPDATE_COMPUTER_CONTROLER = new UpdateComputerController();
	
	private UpdateComputerView updateComputerView;
	
	private CrudComputer crudComputer;
	
	private UpdateComputerController()
	{
		crudComputer = new CrudComputer();
	}
	
	/**
	 * @return an instance of UpdateComputerController
	 */
	public static UpdateComputerController getInstance() {
		return UPDATE_COMPUTER_CONTROLER;
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
	 * @param pChoice
	 * @throws Exception 
	 */
	public void findComputerById(int pChoice) throws Exception {
		updateComputerView.displayDetailsToUpdate(crudComputer.find(pChoice));		
	}

	/**
	 * Update a computer by using crud method
	 * and call to update view to display MainMenu 
	 * @param computer
	 * @throws Exception 
	 */
	public void update(Computer computer, long id) throws Exception {
		crudComputer.update(computer, id);
		updateComputerView.displayInfoComputer(computer);
	}

}
