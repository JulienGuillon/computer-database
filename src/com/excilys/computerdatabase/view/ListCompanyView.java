package com.excilys.computerdatabase.view;

import java.util.List;
import java.util.Scanner;

import com.excilys.computerdatabase.controler.ListCompanyControler;
import com.excilys.computerdatabase.interfaces.ICompany;

/**
 * @author Guillon Julien
 *
 * 21 févr. 2017
 */
public class ListCompanyView {
	
	private static final ListCompanyView LIST_COMPANY_VIEW = new ListCompanyView();
	
	private ListCompanyControler listCompanyControler;
	
	private ListCompanyView() {
		listCompanyControler = ListCompanyControler.getInstance();
		listCompanyControler.setListCompanyView(this);
	}
	
	public static ListCompanyView getInstance()
	{
		return LIST_COMPANY_VIEW;
	}
	
	public void displayHeader()
	{
		System.out.format(ConstanteView.FORMAT_COMPANY, "ID", "NAME");
	}
	
	public void displayFooter() throws Exception
	{
		String choice;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("\t\t previous(p) \t\t quit(q) \t\t next(n)");
			choice = sc.next();
			listCompanyControler.findCompanies(choice);
		}
		while(!choice.equals("q"));
		sc.close();
		IView.displayMainMenu();
	}
	
	public void displayUI() throws Exception
	{
		listCompanyControler.findCompanies("");
		displayFooter();
	}

	/**
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
