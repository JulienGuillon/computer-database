package com.excilys.computerdatabase.controler;

import com.excilys.computerdatabase.dao.CrudComputer;
import com.excilys.computerdatabase.view.UpdateComputerView;

/**
 * @author Guillon Julien
 *
 * 21 févr. 2017
 */
public class UpdateComputerControler {

	private static final UpdateComputerControler UPDATE_COMPUTER_CONTROLER = new UpdateComputerControler();
	
	private UpdateComputerView updateComputerView;
	
	private CrudComputer crudComputer;
	
	private UpdateComputerControler()
	{
		crudComputer = new CrudComputer();
	}
	
	/**
	 * @return
	 */
	public static UpdateComputerControler getInstance() {
		return UPDATE_COMPUTER_CONTROLER;
	}

	/**
	 * @param pUpdateComputerView
	 */
	public void setUpdateComputerView(UpdateComputerView pUpdateComputerView) {
		this.updateComputerView = pUpdateComputerView;
	}

	/**
	 * @param pChoice
	 */
	public void findComputerById(int pChoice) {
		updateComputerView.displayDetailsToUpdate(crudComputer.find(pChoice));		
	}

}
