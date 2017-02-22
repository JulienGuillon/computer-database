package com.excilys.computerdatabase.validation;

import com.excilys.computerdatabase.entities.Computer;

/**
 * @author Guillon Julien
 *
 * 20 févr. 2017
 * 
 *  Allows to make all verifications on computer
 */
public interface IComputerValidation {
	public static void check(Computer computer) throws Exception
	{
		ICheck.isNull(computer.getName());
		StringCheck.isNotEmpty(computer.getName());
		DateCheck.isBefore(computer.getIntroduced(), computer.getDiscontinued());
	}
}
