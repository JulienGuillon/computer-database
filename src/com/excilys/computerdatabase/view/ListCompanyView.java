package com.excilys.computerdatabase.view;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.excilys.computerdatabase.controller.ListCompanyController;
import com.excilys.computerdatabase.entities.Company;
import com.excilys.computerdatabase.exception.PersistenceException;
import com.excilys.computerdatabase.validation.SelectionValidation;

/**
 * @author Guillon Julien
 *
 * 21 févr. 2017
 * 
 *  View that display list of companies 
 *  
 */
public enum ListCompanyView {
	INSTANCE;
		
	private ListCompanyController listCompanyControler;
	
	private Scanner sc = ScannerInstance.INSTANCE.getScanner();
	
	private ListCompanyView() {
		listCompanyControler = ListCompanyController.INSTANCE;
		listCompanyControler.setListCompanyView(Optional.ofNullable(this));
	}
	
	
	public void displayHeader()
	{
		System.out.format(ConstanteView.FORMAT_COMPANY, "ID", "NAME");
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
			listCompanyControler.findCompanies(Optional.ofNullable(choice));
		}
		while(!choice.equals("q"));
		IView.displayMainMenu();
	}
	
	/**
	 * @throws PersistenceException 
	 * @throws Exception
	 */
	public void displayUI() throws PersistenceException
	{
		System.out.print("Choose size of page: ");
		listCompanyControler.findCompanies(Optional.ofNullable(""));
		displayFooter();
	}

	/**
	 * Display view that show list of companies 
	 * @param optionalCompanies
	 */
	public void displayCompanies(Optional<List<Optional<Company>>> optionalCompanies) {
		displayHeader();
		if(optionalCompanies.isPresent()) {
			Company company;
			for(Optional<Company> optionalCompany : optionalCompanies.get())
			{
				if(optionalCompany.isPresent())
				{
					company = optionalCompany.get();
					System.out.format(ConstanteView.FORMAT_COMPANY, company.getId(), company.getName());
				}
			}
		}
	}

}
