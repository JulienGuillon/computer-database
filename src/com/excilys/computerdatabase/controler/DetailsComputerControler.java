package com.excilys.computerdatabase.controler;

import com.excilys.computerdatabase.dao.CrudComputer;
import com.excilys.computerdatabase.view.DetailsComputerView;

/**
 * @author Guillon Julien
 *
 * 21 févr. 2017
 */
public class DetailsComputerControler {

	private static final DetailsComputerControler DETAILS_COMPUTER_CONTROLER = new DetailsComputerControler();
	
	private DetailsComputerView detailsComputerView;
	
	private CrudComputer crudComputer;

	private DetailsComputerControler()
	{
		crudComputer = new CrudComputer();
	}
	
	public static DetailsComputerControler getInstance()
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
	public void makeOperation(String pOperation, int pId) {
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
