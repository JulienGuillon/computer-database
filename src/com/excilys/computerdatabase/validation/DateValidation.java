package com.excilys.computerdatabase.validation;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Guillon Julien
 *
 * 23 févr. 2017
 */
public class DateValidation {

	private static final Logger LOGGER = LoggerFactory.getLogger(DateValidation.class);
	
	public static boolean formatIsValid(String date)
	{
		if (date.matches("\\d{4}-\\d{2}-\\d{2}"))
		{
			return true;
		}
		LOGGER.info("Date format is not valid !");
		return false;
	}

	public static boolean dateIsValid(LocalDate dateBefore, LocalDate dateAfter)
	{
		if (dateBefore.isBefore(dateAfter))
		{
			return true;
		}
		LOGGER.info("Introducing date can't be after discontinuing date !");
		return false;
	}
}
