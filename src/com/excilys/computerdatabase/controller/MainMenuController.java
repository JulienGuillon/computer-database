package com.excilys.computerdatabase.controller;

import com.excilys.computerdatabase.view.IView;
import com.excilys.computerdatabase.view.MainMenuView;

/**
 * @author Guillon Julien
 *
 * 21 févr. 2017
 * 
 * Controller for the MainMenuView
 * It is a singleton.
 * Allows to catch event on view MainMenuView and make validation.
 * 
 */
public enum MainMenuController {
	INSTANCE;
	
	private MainMenuView mainMenuView;
	
	private MainMenuController()
	{
	}

	/**
	 * Control user entry and call the good view to display
	 * 
	 * @param choice
	 * @throws Exception 
	 */
	public void controlUserChoice(String choice)
	{
		switch (choice)
		{
		case "1":
			IView.displayComputers();
			break;
		case "2":
			IView.displayCompanies();
			break;
		case "3":
			IView.displayComputerDetails();
			break;
		case "4":
			break;
		case "5":
			IView.displayComputerUpdate();
			break;
		case "6":
			break;
		default:
			break;
		}
	}
	
	/**
	 * @param mainMenuView the mainMenuView to set
	 */
	public void setMainMenuView(MainMenuView pMainMenuView) {
		this.mainMenuView = pMainMenuView;
	}
	
}
