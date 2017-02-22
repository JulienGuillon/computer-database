package com.excilys.computerdatabase.validation;

import com.excilys.computerdatabase.entities.Company;

/**
 * @author Guillon Julien
 *
 * 20 févr. 2017
 * 
 * Allows to make all verifications on computer 
 */
public interface ICompanyValidation {
	public static void check(Company company) throws Exception
	{
		ICheck.isNull(company.getName());
		StringCheck.isNotEmpty(company.getName());
	}
}
