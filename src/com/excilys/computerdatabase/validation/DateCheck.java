package com.excilys.computerdatabase.validation;

import java.time.DateTimeException;
import java.util.Date;

/**
 * @author Guillon Julien
 *
 * 20 févr. 2017
 */
public class DateCheck implements ICheck{
	
	public static void isBefore(Date pIntroduced, Date pDiscontinued) throws Exception
	{
		if (pDiscontinued != null && pIntroduced != null)
		{
			if (!pIntroduced.before(pDiscontinued)) throw new DateTimeException("Introducing date can't be after discontinuing date");
		}
	}
}
