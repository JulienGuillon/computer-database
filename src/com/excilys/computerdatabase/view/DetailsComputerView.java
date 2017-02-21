package com.excilys.computerdatabase.view;

import java.util.Scanner;

import com.excilys.computerdatabase.controler.DetailsComputerControler;
import com.excilys.computerdatabase.interfaces.IComputer;

/**
 * @author Guillon Julien
 *
 * 21 févr. 2017
 */
public class DetailsComputerView {

	private static final DetailsComputerView DETAILS_COMPUTER_VIEW = new DetailsComputerView();
	
	private DetailsComputerControler detailsComputerControler;

	private int idComputer;
	
	private Scanner sc;
	
	private DetailsComputerView()
	{
		detailsComputerControler = DetailsComputerControler.getInstance();
		detailsComputerControler.setDetailsComputerView(this);
		sc = new Scanner(System.in);
	}
	
	public static DetailsComputerView getInstance()
	{
		return DETAILS_COMPUTER_VIEW;
	}
	
	public void displayHeader()
	{
		System.out.println("\t\t SHOW COMPUTER DETAIL \t\t");
	}
	
	public void displayFooter()
	{
		System.out.println("\t\t delete(d) \t\t update(u) \t\t");
		detailsComputerControler.makeOperation(sc.next(), idComputer);
	}
	
	public void displayUI() throws Exception
	{
		int choice;
		displayHeader();
		do {
			System.out.print("Select computer's id to show its details: ");
			choice = sc.nextInt();
		}
		while(choice <= 0);
		detailsComputerControler.findComputerById(choice);
		displayFooter();
	}

	/**
	 * @param pComputer
	 */
	public void displayDetails(IComputer pComputer) {
		System.out.format(ConstanteView.FORMAT_COMPUTER, "ID", "NOM", "INTRODUCED", "DISCONTINUED", "COMPANY");
		System.out.format(ConstanteView.FORMAT_COMPUTER, pComputer.getId(), pComputer.getName(),
				(pComputer.getIntroduced()== null) ? "" : pComputer.getIntroduced(),
				(pComputer.getDiscontinued()== null) ? "" : pComputer.getDiscontinued(),
				(pComputer.getManufacturer() == null) ? "" : pComputer.getManufacturer().getName());
		idComputer = pComputer.getId();
	}

	/**
	 * 
	 */
	public void displayDeletion(int pId) {
		System.out.println("Computer with ID: " + pId + " was deleted");
	}
}
