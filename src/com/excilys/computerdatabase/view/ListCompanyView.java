package com.excilys.computerdatabase.view;

import java.util.List;
import java.util.Scanner;

import com.excilys.computerdatabase.controller.ListCompanyController;
import com.excilys.computerdatabase.interfaces.ICompany;

/**
 * @author Guillon Julien
 *
 * 21 févr. 2017
 * 
 *  View that display list of companies 
 *  
 */
public class ListCompanyView {
	
	private static final ListCompanyView LIST_COMPANY_VIEW = new ListCompanyView();
	
	private ListCompanyController listCompanyControler;
	
	private Scanner sc = ScannerInstance.getInstance();
	
	private ListCompanyView() {
		listCompanyControler = ListCompanyController.getInstance();
		listCompanyControler.setListCompanyView(this);
	}
	
	/**
	 * 
	 * @return an instance of ListCompanyView
	 */
	public static ListCompanyView getInstance()
	{
		return LIST_COMPANY_VIEW;
	}
	
	public void displayHeader()
	{
		System.out.format(ConstanteView.FORMAT_COMPANY, "ID", "NAME");
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
			listCompanyControler.findCompanies(choice);
		}
		while(!choice.equals("q"));
		IView.displayMainMenu();
	}
	
	/**
	 * @throws Exception
	 */
	public void displayUI() throws Exception
	{
		listCompanyControler.findCompanies("");
		displayFooter();
	}

	/**
	 * Display view that show list of companies 
	 * @param pCompanies
	 */
	public void displayCompanies(List<ICompany> pCompanies) {
		displayHeader();
		for(ICompany c : pCompanies)
		{
			System.out.format(ConstanteView.FORMAT_COMPANY, c.getId(), c.getName());
		}
	}

}
