package com.excilys.computerdatabase.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Test;

import com.excilys.computerdatabase.validation.DateValidation;

/**
 * @author Guillon Julien
 *
 *         24 févr. 2017
 */
public class DateValidationTest {

    /**
     * Test method that check date format with valid date.
     */
    @Test
    public void formatIsValid() {
        assertTrue(DateValidation.formatIsValid(Optional.of("2000-12-01")));
        assertTrue(DateValidation.formatIsValid(Optional.of("0000-00-00")));
    }

    /**
     * Test method that check date format with non valid date.
     */
    @Test
    public void formatIsNotValid() {
        assertFalse(DateValidation.formatIsValid(Optional.of("")));
        assertFalse(DateValidation.formatIsValid(Optional.of("    ")));
        assertFalse(DateValidation.formatIsValid(Optional.of("azerty")));
        assertFalse(DateValidation.formatIsValid(Optional.of("yyyy-mm-dd")));
        assertFalse(DateValidation.formatIsValid(Optional.of("12334")));
        assertFalse(DateValidation.formatIsValid(Optional.of("1985/12/01")));
        assertFalse(DateValidation.formatIsValid(Optional.of("1985 12 01")));
        assertFalse(DateValidation.formatIsValid(Optional.of("05-12-1994")));
        assertFalse(DateValidation.formatIsValid(Optional.of(" 1985-12-01 ")));
        assertFalse(DateValidation.formatIsValid(Optional.of("198d-12-01")));
        assertFalse(DateValidation.formatIsValid(Optional.ofNullable(null)));
    }

    /**
     *
     */
    @Test
    public void dateIsValid() {
        LocalDate date1 = LocalDate.parse("2000-01-01");
        LocalDate date2 = LocalDate.parse("2001-01-01");
        assertTrue(DateValidation.dateIsValid(Optional.ofNullable(date1), Optional.ofNullable(date2)));
        date2 = LocalDate.parse("2000-01-02");
        assertTrue(DateValidation.dateIsValid(Optional.ofNullable(date1), Optional.ofNullable(date2)));
        date2 = LocalDate.parse("2000-02-01");
        assertTrue(DateValidation.dateIsValid(Optional.ofNullable(date1), Optional.ofNullable(date2)));
    }

    /**
     *
     */
    @Test
    public void dateIsNotValid() {
        LocalDate date1 = LocalDate.parse("2000-01-01");
        LocalDate date2 = LocalDate.parse("2000-01-01");
        assertFalse(DateValidation.dateIsValid(Optional.ofNullable(date1), Optional.ofNullable(date2)));
        date2 = LocalDate.parse("1999-01-01");
        assertFalse(DateValidation.dateIsValid(Optional.ofNullable(date1), Optional.ofNullable(date2)));
        date2 = null;
        assertFalse(DateValidation.dateIsValid(Optional.ofNullable(date1), Optional.ofNullable(date2)));
        date1 = null;
        date2 = LocalDate.parse("2000-01-01");
        assertFalse(DateValidation.dateIsValid(Optional.ofNullable(date1), Optional.ofNullable(date2)));
        date2 = null;
        assertFalse(DateValidation.dateIsValid(Optional.ofNullable(date1), Optional.ofNullable(date2)));
    }
}
