package com.excilys.computerdatabase.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerdatabase.exception.PersistenceException;
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
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MainMenuController.class);

	private MainMenuView mainMenuView;
	
	private MainMenuController()
	{
	}

	/**
	 * Control user entry and call the good view to display
	 * 
	 * @param choice
	 * @throws PersistenceException 
	 * @throws Exception 
	 */
	public void controlUserChoice(Optional<String> optionalChoice) {	
		if(optionalChoice.isPresent()) {
			String choice = optionalChoice.get();
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
				LOGGER.info("Selection is not valid, should be number 1-6 or q !");
				break;
			}
		}
	}
	
	/**
	 * @param mainMenuView the mainMenuView to set
	 */
	public void setMainMenuView(Optional<MainMenuView> optionalMainMenuView) {
		if(optionalMainMenuView.isPresent()) {
			this.mainMenuView = optionalMainMenuView.get();
		}
	}
	
}
