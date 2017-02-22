package com.excilys.computerdatabase.view;

import java.util.Scanner;

import com.excilys.computerdatabase.controller.DetailsComputerController;
import com.excilys.computerdatabase.interfaces.IComputer;

/**
 * @author Guillon Julien
 *
 * 21 févr. 2017
 * 
 * View that display informations about one computer
 * 
 */
public class DetailsComputerView {

	private static final DetailsComputerView DETAILS_COMPUTER_VIEW = new DetailsComputerView();
	
	private DetailsComputerController detailsComputerControler;

	private int idComputer;
	
	private Scanner sc = ScannerInstance.getInstance();
	
	private DetailsComputerView()
	{
		detailsComputerControler = DetailsComputerController.getInstance();
		detailsComputerControler.setDetailsComputerView(this);
	}
	
	/**
	 * @return an instance of DetailsComputerView
	 */
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
	
	/**
	 * Display view to select computer's id
	 * and use controller to get associated computer
	 * @throws Exception
	 */
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
		IView.displayMainMenu();
	}

	/**
	 * Display details of a computer
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
	 * Display a message to confirm deletion of a computer
	 */
	public void displayDeletion(int pId) {
		System.out.println("Computer with ID: " + pId + " was deleted");
	}
}
