package com.excilys.computerdatabase.validation;

import com.excilys.computerdatabase.interfaces.ICompany;

/**
 * @author Guillon Julien
 *
 * 20 févr. 2017
 * 
 * Allows to make all verifications on computer 
 */
public interface ICompanyValidation {
	public static void check(ICompany pCompany) throws Exception
	{
		ICheck.isNull(pCompany.getName());
		StringCheck.isNotEmpty(pCompany.getName());
	}
}
