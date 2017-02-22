package com.excilys.computerdatabase.view;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.excilys.computerdatabase.controller.ListComputerController;
import com.excilys.computerdatabase.entities.Computer;

/**
 * @author Guillon Julien
 *
 * 21 févr. 2017
 * 
 * View that display list of computers
 * 
 */
public enum ListComputerView {
	INSTANCE;
		
	private ListComputerController listComputerControler;
	
	private Scanner sc = ScannerInstance.INSTANCE.getScanner();
	
	private ListComputerView() {
		listComputerControler = ListComputerController.getInstance();
		listComputerControler.setListComputerView(this);
	}
	
	
	public void displayHeader()
	{
		System.out.format(ConstanteView.FORMAT_COMPUTER, "ID", "NOM", "INTRODUCED", "DISCONTINUED", "COMPANY");
	}


	 /**
	  * Display footer that able to select next page or previous page
	  * @throws Exception
	  */
	 public void displayFooter() throws Exception
	 {
		String choice;
		do {
			System.out.println("\t\t previous(p) \t\t quit(q) \t\t next(n)");
			choice = sc.next();
			listComputerControler.findComputers(choice);
		}
		while(!choice.equals("q"));
		IView.displayMainMenu();
	}
	
	public void displayUI() throws Exception
	{
		displayHeader();
		listComputerControler.findComputers("");
		displayFooter();
	}

	/**
	 * Display view that show list of computers
	 * @param pComputers
	 * @throws SQLException 
	 */
	public void displayComputers(List<Computer> computers) throws SQLException {
		for(Computer c : computers)
		{
			System.out.format(ConstanteView.FORMAT_COMPUTER, c.getId(), c.getName(),
					(c.getIntroduced()== null) ? "" : c.getIntroduced(),
					(c.getDiscontinued()== null) ? "" : c.getDiscontinued(),
					(c.getManufacturer() == null) ? "" : c.getManufacturer().getName());
		}
	}
	
	
	
	

}
