package com.excilys.computerdatabase.controller;

import java.util.List;
import java.util.Optional;

import com.excilys.computerdatabase.dao.impl.CrudCompany;
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
public enum ListCompanyController {
	INSTANCE;
	
	private ListCompanyView listCompanyView;
	
	private CrudCompany crudCompany;
	
	private int offset;

	private ListCompanyController()
	{
		crudCompany = new CrudCompany();
	}
		
	/**
	 * Find all companies with pagination by using crud method
	 * and call to update view 
	 * @param choice
	 */
	public void findCompanies(String choice)
	{
		boolean quit = false;
		switch(choice)
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
			Optional<List<Optional<Company>>> optionalCompanies = crudCompany.findUsingPagination(offset);
			if (optionalCompanies.isPresent())
			{
				List<Optional<Company>> companies = optionalCompanies.get();
				listCompanyView.displayCompanies(companies);
			}
		}
	}
	
	/**
	 * @param listCompanyView the listCompanyView to set
	 */
	public void setListCompanyView(ListCompanyView listCompanyView) {
		this.listCompanyView = listCompanyView;
	}
}
