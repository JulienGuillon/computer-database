package com.excilys.computerdatabase.view;

import java.sql.SQLException;

/**
 * @author Guillon Julien
 *
 * 21 févr. 2017
 */
public interface IView {
	public static void displayMainMenu()
	{
		MainMenuView.getInstance().displayUI();
	}
	
	public static void displayComputers() throws SQLException
	{
		ListComputerView.getInstance().displayUI();
	}

}
