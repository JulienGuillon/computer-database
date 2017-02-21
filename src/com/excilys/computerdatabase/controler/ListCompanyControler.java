package com.excilys.computerdatabase.controler;

import java.util.List;

import com.excilys.computerdatabase.dao.CrudCompany;
import com.excilys.computerdatabase.interfaces.ICompany;
import com.excilys.computerdatabase.view.ListCompanyView;

/**
 * @author Guillon Julien
 *
 * 21 févr. 2017
 */
public class ListCompanyControler {

	private static final ListCompanyControler LIST_COMPANY_CONTROLER = new ListCompanyControler();

	private ListCompanyView listCompanyView;
	
	private CrudCompany crudCompany;
	
	private int offset;

	private ListCompanyControler()
	{
		offset = 0;
		crudCompany = new CrudCompany();
	}
	
	public static ListCompanyControler getInstance()
	{
		return LIST_COMPANY_CONTROLER;
	}
	
	public void findCompanies(String pChoice)
	{
		boolean quit = false;
		switch(pChoice)
		{
			case "n":
				offset = offset+Constante.PAGE_SIZE;
				break;
			case "p":
				break;
			case "q":
				break;
		}
		if (!quit)
		{
			List<ICompany> companies = crudCompany.findUsingPagination(offset);
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
