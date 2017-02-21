package com.excilys.computerdatabase.view;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.computerdatabase.Controler.ListComputerControler;
import com.excilys.computerdatabase.Controler.MainMenuControler;

/**
 * @author Guillon Julien
 *
 * 21 févr. 2017
 */
public class ListComputerView {
	
	private static final ListComputerView LIST_COMPUTER_VIEW = new ListComputerView();
	
	private ListComputerControler listComputerControler;
	
	private ListComputerView() {
		listComputerControler = ListComputerControler.getInstance();
		listComputerControler.setListComputerView(this);
	}
	
	public static ListComputerView getInstance()
	{
		return LIST_COMPUTER_VIEW;
	}
	
	public void displayUI() throws SQLException
	{
		System.out.println("Computers list: ");
		listComputerControler.findComputers();
	}

	/**
	 * @param pResultSet
	 * @throws SQLException 
	 */
	public void displayComputers(ResultSet pResultSet) throws SQLException {
		while(pResultSet.next())
		{
			System.out.println(pResultSet.getString("name"));
		}
	}
	
	

}
