package com.excilys.computerdatabase.view;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.excilys.computerdatabase.controller.ListComputerController;
import com.excilys.computerdatabase.entities.Computer;
import com.excilys.computerdatabase.exception.PersistenceException;
import com.excilys.computerdatabase.validation.SelectionValidation;

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
		listComputerControler = ListComputerController.INSTANCE;
		listComputerControler.setListComputerView(Optional.ofNullable(this));
	}
	
	
	public void displayHeader()
	{
		System.out.format(ConstanteView.FORMAT_COMPUTER, "ID", "NOM", "INTRODUCED", "DISCONTINUED", "COMPANY");
	}


	 /**
	  * Display footer that able to select next page or previous page
	 * @throws PersistenceException 
	  * @throws Exception
	  */
	 public void displayFooter() throws PersistenceException
	 {
		String choice;
		do {
			System.out.println("\t\t previous(p) \t\t quit(q) \t\t next(n)");
			choice = sc.next();
			while (!SelectionValidation.userChoiceIsValid(Optional.ofNullable(choice)))
			{	
				choice = sc.next();
				System.out.println("\t\t previous(p) \t\t quit(q) \t\t next(n)");
			}
			listComputerControler.findComputers(Optional.ofNullable(choice));
		}
		while(!choice.equals("q"));
		IView.displayMainMenu();
	}
	
	public void displayUI() throws PersistenceException
	{
		displayHeader();
		listComputerControler.findComputers(Optional.ofNullable(""));
		displayFooter();
	}

	/**
	 * Display view that show list of computers
	 * @param pComputers
	 * @throws SQLException 
	 */
	public void displayComputers(List<Optional<Computer>> computers) 
	{
		Computer computer;
		for(Optional<Computer> optionalComputer : computers)
		{
			if(optionalComputer.isPresent())
			{
				computer = optionalComputer.get();
				System.out.format(ConstanteView.FORMAT_COMPUTER, computer.getId(), computer.getName(),
						(computer.getIntroduced()== null) ? "" : computer.getIntroduced(),
						(computer.getDiscontinued()== null) ? "" : computer.getDiscontinued(),
						(computer.getManufacturer() == null) ? "" : computer.getManufacturer().getName());
			}
		}
	}

}
