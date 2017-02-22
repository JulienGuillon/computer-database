package com.excilys.computerdatabase.view;

import java.util.Scanner;

import com.excilys.computerdatabase.controller.MainMenuController;

/**
 * @author Guillon Julien
 *
 * 21 févr. 2017
 * 
 * View that display main menu 
 * 
 */
public enum MainMenuView {
	INSTANCE;
	
	private MainMenuController mainMenuControler;

	private String choice;
	
	private	Scanner sc = ScannerInstance.INSTANCE.getScanner();
	
	private MainMenuView()
	{
		mainMenuControler = MainMenuController.getInstance();
		mainMenuControler.setMainMenuView(this);
	}
	
	
	/**
     * Display menu that propose different options 
     * and catch user selection
	 * @throws Exception
	 */
	public void displayUI() throws Exception
	{
		System.out.println("\t\t SELECT OPTION \t\t");
		System.out.println("[1] List computers");
		System.out.println("[2] List companies");
		System.out.println("[3] Show computer details");
		System.out.println("[4] Create a computer");
		System.out.println("[5] Update a computer");
		System.out.println("[6] Delete a computer");
		takeUserChoice();
	}
	
	/**
	 * @throws Exception
	 */
	public void takeUserChoice() throws Exception
	{
		choice = sc.next();
		mainMenuControler.controlUserChoice(choice);
	}

	/**
	 * @param pChoice
	 * @throws Exception 
	 */
	public void displayError(String pChoice) throws Exception {
		System.out.println("Your selection "+ pChoice +" is not a valid option.");
		displayUI();
		takeUserChoice();
	}

}
