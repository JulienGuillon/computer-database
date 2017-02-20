package com.excilys.computerdatabase.validation;

/**
 * @author Guillon Julien
 *
 * 20 févr. 2017
 */
public class StringCheck implements ICheck{
	public static void isNotEmpty(String pString) throws Exception
	{
		if(pString.equals("")) throw new Exception("A string can't be empty");
	}
	
	public static void isValid(String pString) throws Exception
	{
		if(pString.matches("")) throw new IllegalArgumentException();
	}

}
