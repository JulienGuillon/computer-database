package com.excilys.computerdatabase.view;

import java.util.Date;
import java.util.Scanner;

import com.excilys.computerdatabase.controler.DetailsComputerControler;
import com.excilys.computerdatabase.controler.UpdateComputerControler;
import com.excilys.computerdatabase.interfaces.IComputer;

/**
 * @author Guillon Julien
 *
 * 21 févr. 2017
 */
public class UpdateComputerView {
	
	private static final UpdateComputerView UPDATE_COMPUTER_VIEW = new UpdateComputerView();
	
	private UpdateComputerControler updateComputerControler;
	
	private Scanner sc;
	
	private UpdateComputerView()
	{
		updateComputerControler = UpdateComputerControler.getInstance();
		updateComputerControler.setUpdateComputerView(this);
		sc = new Scanner(System.in);
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
	 */
	public void displayDetailsToUpdate(IComputer pComputer) {
		System.out.println("ID (" + pComputer.getId() + ")");
		String name = "";
		Date introduced;
		Date discontinued;
		do
		{
			System.out.print("NAME (" + pComputer.getName() + "): ");
			name = sc.nextLine();
		}
		while(name.isEmpty());
		System.out.println(name);
		System.out.print("INTRODUCED (" + pComputer.getIntroduced() + "): ");
	}
	
	
	

}
