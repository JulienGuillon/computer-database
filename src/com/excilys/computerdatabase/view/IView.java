package com.excilys.computerdatabase.view;


/**
 * @author Guillon Julien
 *
 * 21 févr. 2017
 */
public interface IView {
	public static void displayMainMenu() throws Exception
	{
		MainMenuView.getInstance().displayUI();
	}
	
	public static void displayComputers() throws Exception
	{
		ListComputerView.getInstance().displayUI();
	}
	
	public static void displayCompanies() throws Exception
	{
		ListCompanyView.getInstance().displayUI();
	}
	
	public static void displayComputerDetails() throws Exception
	{
		DetailsComputerView.getInstance().displayUI();
	}
	
	public static void displayComputerUpdate() throws Exception
	{
		UpdateComputerView.getInstance().displayUI();
	}

}
