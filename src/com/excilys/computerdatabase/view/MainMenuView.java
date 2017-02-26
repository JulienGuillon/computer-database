package com.excilys.computerdatabase.view;

import java.util.Optional;
import java.util.Scanner;

import com.excilys.computerdatabase.controller.MainMenuController;
import com.excilys.computerdatabase.exception.PersistenceException;
import com.excilys.computerdatabase.validation.SelectionValidation;

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
		mainMenuControler = MainMenuController.INSTANCE;
		mainMenuControler.setMainMenuView(Optional.ofNullable(this));
	}
	
	public void displayMenu()
	{
		System.out.println("\t\t SELECT OPTION \t\t");
		System.out.println("[1] List computers");
		System.out.println("[2] List companies");
		System.out.println("[3] Show computer details");
		System.out.println("[4] Create a computer");
		System.out.println("[5] Update a computer");
		System.out.println("[6] Delete a computer");
	}
	
	/**
     * Display menu that propose different options 
     * and catch user selection
	 * @throws PersistenceException 
	 */
	public void displayUI() throws PersistenceException
	{
		displayMenu();
		takeUserChoice();
	}
	

	public void takeUserChoice() throws PersistenceException
	{
		choice = sc.next();
		while (!SelectionValidation.userSelectionIsValid(Optional.ofNullable(choice)))
		{	
			choice = sc.next();
			displayMenu();
		}
		mainMenuControler.controlUserChoice(Optional.ofNullable(choice));
	}

}
