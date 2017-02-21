package com.excilys.computerdatabase.controler;

import java.sql.SQLException;
import java.util.List;

import com.excilys.computerdatabase.dao.CrudComputer;
import com.excilys.computerdatabase.interfaces.IComputer;
import com.excilys.computerdatabase.view.ListComputerView;

/**
 * @author Guillon Julien
 *
 * 21 févr. 2017
 */
public class ListComputerControler {
	
	private static final ListComputerControler LIST_COMPUTER_CONTROLER = new ListComputerControler();

	private ListComputerView listComputerView;
	
	private CrudComputer crudComputer;
	
	private int offset;

	private ListComputerControler()
	{
		offset = 0;
		crudComputer = new CrudComputer();
	}
	
	public static ListComputerControler getInstance()
	{
		return LIST_COMPUTER_CONTROLER;
	}

	/**
	 * @throws SQLException 
	 * 
	 */
	public void findComputers(String pChoice) throws SQLException {
		boolean quit = false;
		switch(pChoice)
		{
		case "n":
			offset = offset+Constante.PAGE_SIZE;
			break;
		case "p":
			offset = (offset-Constante.PAGE_SIZE >= 0) ? offset-Constante.PAGE_SIZE : 0;
			break;
		case "q":
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
