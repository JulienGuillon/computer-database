package com.excilys.computerdatabase.view;


/**
 * @author Guillon Julien
 *
 * 21 févr. 2017
 * 
 * Allows to select a view to display
 * 
 */
public interface IView {
	public static void displayMainMenu()
	{
		MainMenuView.INSTANCE.displayUI();
	}
	
	public static void displayComputers()
	{
		ListComputerView.INSTANCE.displayUI();
	}
	
	public static void displayCompanies()
	{
		ListCompanyView.INSTANCE.displayUI();
	}
	
	public static void displayComputerDetails()
	{
		DetailsComputerView.INSTANCE.displayUI();
	}
	
	public static void displayComputerUpdate()
	{
		UpdateComputerView.INSTANCE.displayUI();
	}

}
