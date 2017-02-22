package com.excilys.computerdatabase.controller;

import java.sql.SQLException;
import java.util.List;

import com.excilys.computerdatabase.dao.CrudComputer;
import com.excilys.computerdatabase.interfaces.IComputer;
import com.excilys.computerdatabase.view.ListComputerView;

/**
 * @author Guillon Julien
 *
 * 21 févr. 2017
 * 
 * Controller for the ListComputerView
 * It is a singleton.
 * Allows to catch event on view ListComputerView and make validation,
 * 
 */
public class ListComputerController {
	
	private static final ListComputerController LIST_COMPUTER_CONTROLER = new ListComputerController();

	private ListComputerView listComputerView;
	
	private CrudComputer crudComputer;
	
	private  int offset;

	private ListComputerController()
	{
		crudComputer = new CrudComputer();
	}
	
	/**
	 * 
	 * @return an instance of ListComputerController
	 */
	public static ListComputerController getInstance()
	{	
		return LIST_COMPUTER_CONTROLER;
	}

	/**
	 * Find all computers with pagination by using crud method
	 * and call to update view 
	 * @param pChoice: could be "n" or "p" to switch of page 
	 * @throws SQLException 
	 * 
	 */
	public void findComputers(String pChoice) throws SQLException {
		boolean quit = false;
		switch(pChoice)
		{
		case "n":
			offset = offset+Constant.PAGE_SIZE;
			break;
		case "p":
			offset = (offset-Constant.PAGE_SIZE >= 0) ? offset-Constant.PAGE_SIZE : 0;
			break;
		case "q":
			offset = 0;
			quit = true;
			break;
		}
		if (!quit)
		{
			List<IComputer> computers = crudComputer.findUsingPagination(offset);
			listComputerView.displayComputers(computers);
		}
	}
	
	/**
	 * @param pListComputerView the listComputerView to set
	 */
	public void setListComputerView(ListComputerView pListComputerView) {
		this.listComputerView = pListComputerView;
	}
	
}
