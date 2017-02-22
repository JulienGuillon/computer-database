package com.excilys.computerdatabase.entities;

import com.excilys.computerdatabase.interfaces.ICompany;
import com.excilys.computerdatabase.validation.ICheck;
import com.excilys.computerdatabase.validation.ICompanyValidation;

/**
 * @author Guillon Julien
 *
 * 20 févr. 2017
 */
public class Company implements ICompany{
	
	private int id;
	private String name;
	
	/**
	 * 
	 * @param pCompanyBuilder
	 */
	private Company(CompanyBuilder pCompanyBuilder)
	{
		super();
		name = pCompanyBuilder.name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String pName) throws Exception {
		ICheck.isNull(pName);
		name = pName;
	}
	
	public static class CompanyBuilder {
		private String name;
		
		public CompanyBuilder(String pName)
		{
			name = pName;
		}
		
		public ICompany build() throws Exception {
			ICompany company = new Company(this);
			ICompanyValidation.check(company);
			return company;

		}
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
}
