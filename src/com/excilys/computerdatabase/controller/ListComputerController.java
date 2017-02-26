package com.excilys.computerdatabase.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.excilys.computerdatabase.dao.impl.CrudComputerImpl;
import com.excilys.computerdatabase.entities.Computer;
import com.excilys.computerdatabase.exception.PersistenceException;
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
public enum ListComputerController {
	INSTANCE;
	
	private ListComputerView listComputerView;
	
	private CrudComputerImpl crudComputer;
	
	private  int offset;

	private ListComputerController()
	{
		crudComputer = new CrudComputerImpl();
	}

	/**
	 * Find all computers with pagination by using crud method
	 * and call to update view 
	 * @param pChoice: could be "n" or "p" to switch of page 
	 * @throws PersistenceException 
	 * @throws SQLException 
	 * 
	 */
	public void findComputers(Optional<String> optionalChoice) throws PersistenceException {
		if(optionalChoice.isPresent()) {
			boolean quit = false;
			String choice = optionalChoice.get();
			switch(choice)
			{
			case "n":
				offset = (offset+1);
				break;
			case "p":
				offset = ((offset-1) >= 0) ? (offset-1) : -1;
				break;
			case "q":
				offset = -1;
				quit = true;
				break;
			}
			if (!quit && offset >= 0)
			{
				Optional<List<Optional<Computer>>> optionalComputers = crudComputer.findUsingPagination(offset*Constant.PAGE_SIZE);
				if (optionalComputers.isPresent())
				{
					List<Optional<Computer>> computers = optionalComputers.get();
					listComputerView.displayComputers(computers);
				}
			}
		}
	}
	
	/**
	 * @param listComputerView the listComputerView to set
	 */
	public void setListComputerView(Optional<ListComputerView> optionalListComputerView) {
		if (optionalListComputerView.isPresent()) {
			this.listComputerView = optionalListComputerView.get();
		}
	}
	
}
