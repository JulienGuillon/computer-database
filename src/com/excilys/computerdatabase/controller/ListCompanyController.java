package com.excilys.computerdatabase.controller;

import java.util.List;

import com.excilys.computerdatabase.dao.CrudCompany;
import com.excilys.computerdatabase.entities.Company;
import com.excilys.computerdatabase.view.ListCompanyView;

/**
 * @author Guillon Julien
 *
 * 21 févr. 2017
 * 
 * Controller for the ListCompanyView
 * It is a singleton.
 * Allows to catch event on view ListCompanyView and make validation.
 * 
 */
public class ListCompanyController {

	private static final ListCompanyController LIST_COMPANY_CONTROLER = new ListCompanyController();

	private ListCompanyView listCompanyView;
	
	private CrudCompany crudCompany;
	
	private int offset;

	private ListCompanyController()
	{
		crudCompany = new CrudCompany();
	}
	
	/**
	 * 
	 * @return an instance ListCompanyController
	 */
	public static ListCompanyController getInstance()
	{
		return LIST_COMPANY_CONTROLER;
	}
	
	/**
	 * Find all companies with pagination by using crud method
	 * and call to update view 
	 * @param pChoice
	 */
	public void findCompanies(String pChoice)
	{
		boolean quit = false;
		switch(pChoice)
		{
			case "n":
				offset = offset+Constant.PAGE_SIZE;
				break;
			case "p":
				break;
			case "q":
				offset = 0;
				quit = true;
				break;
		}
		if (!quit)
		{
			List<Company> companies = crudCompany.findUsingPagination(offset);
			listCompanyView.displayCompanies(companies);
		}
	}
	
	/**
	 * @param pListCompanyView the listCompanyView to set
	 */
	public void setListCompanyView(ListCompanyView pListCompanyView) {
		this.listCompanyView = pListCompanyView;
	}
}
