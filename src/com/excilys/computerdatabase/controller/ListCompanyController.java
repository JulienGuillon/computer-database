package com.excilys.computerdatabase.controller;

import java.util.List;
import java.util.Optional;

import com.excilys.computerdatabase.dao.impl.CrudCompanyImpl;
import com.excilys.computerdatabase.entities.Company;
import com.excilys.computerdatabase.exception.PersistenceException;
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
	
	private CrudCompanyImpl crudCompany;
	
	private int offset;

	private ListCompanyController()
	{
		crudCompany = new CrudCompanyImpl();
	}
		
	/**
	 * Find all companies with pagination by using crud method
	 * and call to update view 
	 * @param choice
	 * @throws PersistenceException 
	 */
	public void findCompanies(Optional<String> optionalChoice) throws PersistenceException
	{
		if(optionalChoice.isPresent()) {
			String choice = optionalChoice.get();
			boolean quit = false;
			switch(choice)
			{
				case "n":
					offset = offset+Constant.PAGE_SIZE;
					break;
				case "p":
					offset = offset-Constant.PAGE_SIZE > 0 ? offset-Constant.PAGE_SIZE : -1;
					break;
				case "q":
					offset = -1;
					quit = true;
					break;
			}
			if (!quit || offset >= 0)
			{
				Optional<List<Optional<Company>>> optionalCompanies = crudCompany.findUsingPagination(offset);
				if (optionalCompanies.isPresent())
				{
					listCompanyView.displayCompanies(optionalCompanies);
				}
			}
		}
	}
	
	/**
	 * @param listCompanyView the listCompanyView to set
	 */
	public void setListCompanyView(Optional<ListCompanyView> optionalListCompanyView) {
		if (optionalListCompanyView.isPresent()) {
			this.listCompanyView = optionalListCompanyView.get();
		}
	}
}
