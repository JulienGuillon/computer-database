package com.excilys.computerdatabase.Controler;

import com.excilys.computerdatabase.dao.CrudComputer;
import com.excilys.computerdatabase.validation.ICheck;
import com.excilys.computerdatabase.view.IView;
import com.excilys.computerdatabase.view.MainMenuView;

/**
 * @author Guillon Julien
 *
 * 21 févr. 2017
 */
public class MainMenuControler {

	private static final MainMenuControler MAIN_MENU_CONTROLER = new MainMenuControler();
	private MainMenuView mainMenuView;
	
	private MainMenuControler()
	{
	}
	
	public static MainMenuControler getInstance()
	{
		return MAIN_MENU_CONTROLER;
	}

	/**
	 * @param choice
	 * @throws Exception 
	 */
	public void controlUserChoice(int choice) throws Exception {
		ICheck.isNull(choice);
		switch (choice)
		{
		case 1:
			IView.displayComputers();
			//mainMenuView.displayComputers();
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
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
