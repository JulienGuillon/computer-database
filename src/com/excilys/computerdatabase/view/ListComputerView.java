package com.excilys.computerdatabase.view;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.excilys.computerdatabase.controler.ListComputerControler;
import com.excilys.computerdatabase.interfaces.IComputer;

/**
 * @author Guillon Julien
 *
 * 21 févr. 2017
 */
public class ListComputerView {
	
	private static final ListComputerView LIST_COMPUTER_VIEW = new ListComputerView();
		
	private ListComputerControler listComputerControler;
	
	private ListComputerView() {
		listComputerControler = ListComputerControler.getInstance();
		listComputerControler.setListComputerView(this);
	}
	
	public static ListComputerView getInstance()
	{
		return LIST_COMPUTER_VIEW;
	}
	
	public void displayHeader()
	{
		System.out.format(ConstanteView.FORMAT_COMPUTER, "ID", "NOM", "INTRODUCED", "DISCONTINUED", "COMPANY");
	}
	
	public void displayFooter() throws Exception
	{
		String choice;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("\t\t previous(p) \t\t quit(q) \t\t next(n)");
			choice = sc.next();
			listComputerControler.findComputers(choice);
		}
		while(!choice.equals("q"));
		sc.close();
		IView.displayMainMenu();
	}
	
	public void displayUI() throws Exception
	{
		displayHeader();
		listComputerControler.findComputers("");
		displayFooter();
	}

	/**
	 * @param pComputers
	 * @throws SQLException 
	 */
	public void displayComputers(List<IComputer> pComputers) throws SQLException {
		for(IComputer c : pComputers)
		{
			System.out.format(ConstanteView.FORMAT_COMPUTER, c.getId(), c.getName(),
					(c.getIntroduced()== null) ? "" : c.getIntroduced(),
					(c.getDiscontinued()== null) ? "" : c.getDiscontinued(),
					(c.getManufacturer() == null) ? "" : c.getManufacturer().getName());
		}
	}
	
	
	
	

}
