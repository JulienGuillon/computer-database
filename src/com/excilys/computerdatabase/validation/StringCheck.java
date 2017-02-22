package com.excilys.computerdatabase.validation;

/**
 * @author Guillon Julien
 *
 * 20 févr. 2017
 * 
 *  Allows to make all verifications on string
 */
public class StringCheck implements ICheck{
	
	/**
	 * Check if string is not empty
	 * @param pString
	 * @throws Exception
	 */
	public static void isNotEmpty(String pString) throws Exception
	{
		if(pString.isEmpty()) throw new Exception("A string can't be empty");
	}

}
