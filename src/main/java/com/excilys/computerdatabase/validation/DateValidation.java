package com.excilys.computerdatabase.validation;

import java.time.LocalDate;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Guillon Julien
 *
 *         23 févr. 2017
 */
public class DateValidation {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateValidation.class);

    /**
     * Verify if a string is well formated.
     * @param date string to verify
     * @return boolean
     */
    public static boolean formatIsValid(Optional<String> date) {
        if (date.isPresent()) {
            if (date.get().matches("\\d{4}-\\d{2}-\\d{2}")) {
                return true;
            }
            LOGGER.info("Date format is not valid !");
        }
        return false;
    }


    /**
     * Verify if a date is after an another date.
     * @param dateBefore past date
     * @param dateAfter date to check
     * @return boolean
     */
    public static boolean dateIsValid(Optional<LocalDate> dateBefore, Optional<LocalDate> dateAfter) {
        if (dateBefore.isPresent() && dateAfter.isPresent()) {

            if (dateBefore.get().isBefore(dateAfter.get())) {
                return true;
            }
            LOGGER.info("Introducing date can't be after discontinuing date !");
        }
        return false;
    }
}
