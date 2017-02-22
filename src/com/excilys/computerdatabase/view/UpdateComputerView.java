package com.excilys.computerdatabase.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.excilys.computerdatabase.controller.UpdateComputerController;
import com.excilys.computerdatabase.interfaces.IComputer;

/**
 * @author Guillon Julien
 *
 * 21 févr. 2017
 * 
 * View that allows to select a computer by an id 
 * and modify it to make an update
 */
public class UpdateComputerView {
	
	private static final UpdateComputerView UPDATE_COMPUTER_VIEW = new UpdateComputerView();
	
	private UpdateComputerController updateComputerControler;
	
	private Scanner sc = ScannerInstance.getInstance();
	
	private UpdateComputerView()
	{
		updateComputerControler = UpdateComputerController.getInstance();
		updateComputerControler.setUpdateComputerView(this);
	}
	
	public static UpdateComputerView getInstance()
	{
		return UPDATE_COMPUTER_VIEW;
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
	 * @param pComputer
	 * @throws Exception 
	 */
	public void displayDetailsToUpdate(IComputer pComputer) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("ID (" + pComputer.getId() + ")");
		String name = "";
		Date introduced = null;
		Date discontinued = null;
		String s = null;
		sc.nextLine();
		System.out.print("NAME (" + pComputer.getName() + "): ");
		name = sc.nextLine();
		if(name.equals(""))
		{
			name = pComputer.getName();
		}


		System.out.print("INTRODUCED (" + pComputer.getIntroduced() + "): ");
		s = sc.nextLine();
		if (s.matches("\\d{4}-\\d{2}-\\d{2}"))
		{
			introduced = sdf.parse(s);
		}
		else
		{
			introduced = pComputer.getIntroduced();
		}
		
		s = "";
		System.out.print("DISCONTINUED (" + pComputer.getDiscontinued() + "): ");
		s = sc.nextLine();
		if (s.matches("\\d{4}-\\d{2}-\\d{2}"))
		{
			discontinued = sdf.parse(s);
		}
		else
		{
			discontinued = pComputer.getDiscontinued();
		}
		System.out.println(discontinued);
		pComputer.setIntroduced(introduced);
		pComputer.setDiscontinued(discontinued);
		pComputer.setName(name);
		updateComputerControler.update(pComputer, pComputer.getId());
	}

	/**
	 * @param pComputer
	 * @throws Exception 
	 */
	public void displayInfoComputer(IComputer pComputer) throws Exception {
		System.out.println("Computer with ID: " + pComputer.getId() + " was update");
		IView.displayMainMenu();
	}
	
	
	

}
