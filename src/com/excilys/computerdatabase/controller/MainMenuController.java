package com.excilys.computerdatabase.controller;

import com.excilys.computerdatabase.validation.ICheck;
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
public class MainMenuController {

	private static final MainMenuController MAIN_MENU_CONTROLER = new MainMenuController();
	private MainMenuView mainMenuView;
	
	private MainMenuController()
	{
	}
	
	/**
	 * @return an instance MainMenuController
	 */
	public static MainMenuController getInstance()
	{
		return MAIN_MENU_CONTROLER;
	}

	/**
	 * Control user entry and call the good view to display
	 * 
	 * @param choice
	 * @throws Exception 
	 */
	public void controlUserChoice(String choice) throws Exception {
		ICheck.isNull(choice);
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
			mainMenuView.displayError(choice);
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
