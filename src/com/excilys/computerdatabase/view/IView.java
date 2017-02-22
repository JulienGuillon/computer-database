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
	public static void displayMainMenu() throws Exception
	{
		MainMenuView.INSTANCE.displayUI();
	}
	
	public static void displayComputers() throws Exception
	{
		ListComputerView.INSTANCE.displayUI();
	}
	
	public static void displayCompanies() throws Exception
	{
		ListCompanyView.INSTANCE.displayUI();
	}
	
	public static void displayComputerDetails() throws Exception
	{
		DetailsComputerView.INSTANCE.displayUI();
	}
	
	public static void displayComputerUpdate() throws Exception
	{
		UpdateComputerView.INSTANCE.displayUI();
	}

}
