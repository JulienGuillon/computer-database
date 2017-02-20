package com.excilys.computerdatabase.validation;

/**
 * @author Guillon Julien
 *
 * 20 févr. 2017
 */
public interface ICheck {

	public static void isNull(Object object) throws Exception
	{
		if(object == null) throw new NullPointerException();
	}
}
