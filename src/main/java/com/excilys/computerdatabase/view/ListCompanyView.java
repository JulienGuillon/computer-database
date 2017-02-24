package com.excilys.computerdatabase.view;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.excilys.computerdatabase.controller.ListCompanyController;
import com.excilys.computerdatabase.entities.Company;
import com.excilys.computerdatabase.validation.SelectionValidation;

/**
 * @author Guillon Julien
 *
 ** 21 févr. 2017
 * View that display list of companies
 *
 */
public enum ListCompanyView {
    INSTANCE;
		
	private ListCompanyController listCompanyControler;
	
	private Scanner sc = ScannerInstance.INSTANCE.getScanner();
	
	private ListCompanyView() {
		listCompanyControler = ListCompanyController.INSTANCE;
		listCompanyControler.setListCompanyView(this);
	}
	
	
	public void displayHeader()
	{
		System.out.format(ConstanteView.FORMAT_COMPANY, "ID", "NAME");
	}
	
	/**
	 * Display footer that able to select next page or previous page
	 * @throws Exception
	 */
	public void displayFooter()
	{
		String choice;
		do {
			System.out.println("\t\t previous(p) \t\t quit(q) \t\t next(n)");
			choice = sc.next();
			while (!SelectionValidation.userChoiceIsValid(choice))
			{	
				choice = sc.next();
				System.out.println("\t\t previous(p) \t\t quit(q) \t\t next(n)");
			}
			listCompanyControler.findCompanies(choice);
		}
		while(!choice.equals("q"));
		IView.displayMainMenu();
	}
	
	/**
	 * @throws Exception
	 */
	public void displayUI()
	{
		listCompanyControler.findCompanies("");
		displayFooter();
	}

	/**
	 * Display view that show list of companies 
	 * @param optionalCompanies
	 */
	public void displayCompanies(List<Optional<Company>> optionalCompanies) {
		displayHeader();
		Company company;
		for(Optional<Company> optionalCompany : optionalCompanies)
		{
			if(optionalCompany.isPresent())
			{
				company = optionalCompany.get();
				System.out.format(ConstanteView.FORMAT_COMPANY, company.getId(), company.getName());
			}
		}
	}

}
