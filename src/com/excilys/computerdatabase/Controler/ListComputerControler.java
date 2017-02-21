package com.excilys.computerdatabase.Controler;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.computerdatabase.dao.CrudComputer;
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

	private ListComputerControler()
	{
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
	public void findComputers() throws SQLException {
		ResultSet resultSet = crudComputer.findAll();
		listComputerView.displayComputers(resultSet);
	}
	
	/**
	 * @param pListComputerView the listComputerView to set
	 */
	public void setListComputerView(ListComputerView pListComputerView) {
		this.listComputerView = pListComputerView;
	}
	
}
