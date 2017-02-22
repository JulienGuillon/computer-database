package com.excilys.computerdatabase.controller;

import com.excilys.computerdatabase.dao.CrudComputer;
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
public class DetailsComputerController {

	private static final DetailsComputerController DETAILS_COMPUTER_CONTROLER = new DetailsComputerController();
	
	private DetailsComputerView detailsComputerView;
	
	private CrudComputer crudComputer;

	private DetailsComputerController()
	{
		crudComputer = new CrudComputer();
	}
	
	/**
	 * 
	 * @return an instance of DetailsComputerController
	 */
	public static DetailsComputerController getInstance()
	{
		return DETAILS_COMPUTER_CONTROLER;
	}

	/**
	 * @param pDetailsComputerView
	 */
	public void setDetailsComputerView(DetailsComputerView pDetailsComputerView) {
		this.detailsComputerView = pDetailsComputerView;
	}

	/**
	 * @param pChoice
	 */
	public void findComputerById(int pChoice) {
		detailsComputerView.displayDetails(crudComputer.find(pChoice));
	}

	/**
	 * @param pOperation
	 */
	public void makeOperation(String pOperation, long pId) {
		switch (pOperation)
		{
			case "d":
				crudComputer.delete(pId);
				detailsComputerView.displayDeletion(pId);
				break;
			case "u":
				break;
			default:
				break;
		}
	}


	
	
}
