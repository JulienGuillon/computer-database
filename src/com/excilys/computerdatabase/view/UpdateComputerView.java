package com.excilys.computerdatabase.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.excilys.computerdatabase.controller.UpdateComputerController;
import com.excilys.computerdatabase.entities.Computer;

/**
 * @author Guillon Julien
 *
 * 21 févr. 2017
 * 
 * View that allows to select a computer by an id 
 * and modify it to make an update
 */
public enum UpdateComputerView {
	INSTANCE;
		
	private UpdateComputerController updateComputerControler;
	
	private Scanner sc = ScannerInstance.INSTANCE.getScanner();
	
	private UpdateComputerView()
	{
		updateComputerControler = UpdateComputerController.getInstance();
		updateComputerControler.setUpdateComputerView(this);
	}
	
	
	public void displayHeader()
	{
		System.out.println("\t\t UPDATE COMPUTER \t\t");
	}
	
	public void displayUI() throws Exception
	{
		int choice;
		displayHeader();
		do {
			System.out.print("Select computer's id to modify it: ");
			choice = sc.nextInt();
		}
		while(choice <= 0);
		updateComputerControler.findComputerById(choice);

	}

	/**
	 * @param computer
	 * @throws Exception 
	 */
	public void displayDetailsToUpdate(Computer computer) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("ID (" + computer.getId() + ")");
		String name = "";
		Date introduced = null;
		Date discontinued = null;
		String s = null;
		sc.nextLine();
		System.out.print("NAME (" + computer.getName() + "): ");
		name = sc.nextLine();
		if(name.equals(""))
		{
			name = computer.getName();
		}


		System.out.print("INTRODUCED (" + computer.getIntroduced() + "): ");
		s = sc.nextLine();
		if (s.matches("\\d{4}-\\d{2}-\\d{2}"))
		{
			introduced = sdf.parse(s);
		}
		else
		{
			introduced = computer.getIntroduced();
		}
		
		s = "";
		System.out.print("DISCONTINUED (" + computer.getDiscontinued() + "): ");
		s = sc.nextLine();
		if (s.matches("\\d{4}-\\d{2}-\\d{2}"))
		{
			discontinued = sdf.parse(s);
		}
		else
		{
			discontinued = computer.getDiscontinued();
		}
		System.out.println(discontinued);
		computer.setIntroduced(introduced);
		computer.setDiscontinued(discontinued);
		computer.setName(name);
		updateComputerControler.update(computer, computer.getId());
	}

	/**
	 * @param computer
	 * @throws Exception 
	 */
	public void displayInfoComputer(Computer computer) throws Exception {
		System.out.println("Computer with ID: " + computer.getId() + " was update");
		IView.displayMainMenu();
	}
	
	
	

}
