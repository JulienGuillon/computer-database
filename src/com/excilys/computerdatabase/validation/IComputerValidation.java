package com.excilys.computerdatabase.validation;

import com.excilys.computerdatabase.interfaces.IComputer;

/**
 * @author Guillon Julien
 *
 * 20 févr. 2017
 */
public interface IComputerValidation {
	public static void check(IComputer pComputer) throws Exception
	{
		ICheck.isNull(pComputer.getName());
		StringCheck.isNotEmpty(pComputer.getName());
		StringCheck.isValid(pComputer.getName());
		DateCheck.isBefore(pComputer.getIntroduced(), pComputer.getDiscontinued());
	}
}
