package com.excilys.computerdatabase.view;

import java.util.Scanner;

import com.excilys.computerdatabase.Controler.MainMenuControler;

/**
 * @author Guillon Julien
 *
 * 21 févr. 2017
 */
public class MainMenuView {
	
	private static final MainMenuView MAIN_MENU_VIEW = new MainMenuView();
	
	private MainMenuControler mainMenuControler;

	private int choice;
	
	private MainMenuView()
	{
		mainMenuControler = MainMenuControler.getInstance();
		mainMenuControler.setMainMenuView(this);
	}
	
	public static MainMenuView getInstance()
	{
		return MAIN_MENU_VIEW;
	}
	
	public void displayUI()
	{
		System.out.println("\t\t SELECT OPTION \t\t");
		System.out.println("[1] List computers");
		System.out.println("[2] List companies");
		System.out.println("[3] Show computer details");
		System.out.println("[4] Create a computer");
		System.out.println("[5] Update a computer");
		System.out.println("[6] Delete a computer");
	}
	
	public void takeUserChoice() throws Exception
	{
		Scanner sc = new Scanner(System.in);
		choice = sc.nextInt();
		System.out.println(choice);
		mainMenuControler.controlUserChoice(choice);
		sc.close();
	}
	
	public static void main(String[] args) throws Exception {
		MainMenuView main = MainMenuView.getInstance();
		main.displayUI();
		main.takeUserChoice();
	}

	/**
	 * @param pChoice
	 * @throws Exception 
	 */
	public void displayError(int pChoice) throws Exception {
		System.out.println("Your selection "+ pChoice +" is not a valid option.");
		MAIN_MENU_VIEW.displayUI();
		MAIN_MENU_VIEW.takeUserChoice();
	}

}
