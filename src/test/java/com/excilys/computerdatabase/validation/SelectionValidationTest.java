package com.excilys.computerdatabase.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import com.excilys.computerdatabase.validations.SelectionValidation;

/**
 * @author Guillon Julien
 *
 * 27 févr. 2017
 */
public class SelectionValidationTest {

    /**
     *
     */
    @Test
    public void userChoiceIsValid() {
        assertTrue(SelectionValidation.userChoiceIsValid(Optional.ofNullable("n")));
        assertTrue(SelectionValidation.userChoiceIsValid(Optional.ofNullable("N")));

        assertTrue(SelectionValidation.userChoiceIsValid(Optional.ofNullable("p")));
        assertTrue(SelectionValidation.userChoiceIsValid(Optional.ofNullable("P")));

        assertTrue(SelectionValidation.userChoiceIsValid(Optional.ofNullable("q")));
        assertTrue(SelectionValidation.userChoiceIsValid(Optional.ofNullable("Q")));

        assertTrue(SelectionValidation.userChoiceIsValid(Optional.ofNullable("d")));
        assertTrue(SelectionValidation.userChoiceIsValid(Optional.ofNullable("D")));

        assertTrue(SelectionValidation.userChoiceIsValid(Optional.ofNullable("u")));
        assertTrue(SelectionValidation.userChoiceIsValid(Optional.ofNullable("U")));
    }

    /**
     *
     */
    @Test
    public void userSelectionIsValid() {
        assertTrue(SelectionValidation.userSelectionIsValid(Optional.ofNullable("q")));
        assertTrue(SelectionValidation.userSelectionIsValid(Optional.ofNullable("Q")));
        assertTrue(SelectionValidation.userSelectionIsValid(Optional.ofNullable("1")));
        assertTrue(SelectionValidation.userSelectionIsValid(Optional.ofNullable("2")));
        assertTrue(SelectionValidation.userSelectionIsValid(Optional.ofNullable("3")));
        assertTrue(SelectionValidation.userSelectionIsValid(Optional.ofNullable("4")));
        assertTrue(SelectionValidation.userSelectionIsValid(Optional.ofNullable("5")));
        assertTrue(SelectionValidation.userSelectionIsValid(Optional.ofNullable("6")));
    }

    /**
     *
     */
    @Test
    public void idIsValid() {
        assertTrue(SelectionValidation.idIsValid(Optional.ofNullable("0")));
        assertTrue(SelectionValidation.idIsValid(Optional.ofNullable("01")));
        assertTrue(SelectionValidation.idIsValid(Optional.ofNullable("25")));
    }

    /**
     *
     */
    @Test
    public void userChoiceIsNotValid() {
        assertFalse(SelectionValidation.userChoiceIsValid(Optional.ofNullable("a")));
        assertFalse(SelectionValidation.userChoiceIsValid(Optional.ofNullable("")));
        assertFalse(SelectionValidation.userChoiceIsValid(Optional.ofNullable(" ")));
        assertFalse(SelectionValidation.userChoiceIsValid(Optional.ofNullable("1")));
        assertFalse(SelectionValidation.userChoiceIsValid(Optional.ofNullable("_")));
        assertFalse(SelectionValidation.userChoiceIsValid(Optional.ofNullable("nq")));
        assertFalse(SelectionValidation.userChoiceIsValid(Optional.ofNullable("a")));
        assertFalse(SelectionValidation.userChoiceIsValid(Optional.ofNullable(" a")));
        assertFalse(SelectionValidation.userChoiceIsValid(Optional.ofNullable("a ")));
        assertFalse(SelectionValidation.userChoiceIsValid(Optional.ofNullable(null)));
    }

    /**
     *
     */
    @Test
    public void userSelectionIsNotValid() {
        assertFalse(SelectionValidation.userSelectionIsValid(Optional.ofNullable("")));
        assertFalse(SelectionValidation.userSelectionIsValid(Optional.ofNullable(" ")));
        assertFalse(SelectionValidation.userSelectionIsValid(Optional.ofNullable(null)));
        assertFalse(SelectionValidation.userSelectionIsValid(Optional.ofNullable("a")));
        assertFalse(SelectionValidation.userSelectionIsValid(Optional.ofNullable("12")));
        assertFalse(SelectionValidation.userSelectionIsValid(Optional.ofNullable("7")));
        assertFalse(SelectionValidation.userSelectionIsValid(Optional.ofNullable("0")));
        assertFalse(SelectionValidation.userSelectionIsValid(Optional.ofNullable("-0")));
    }

    /**
     *
     */
    @Test
    public void idIsNotValid() {
        assertFalse(SelectionValidation.idIsValid(Optional.ofNullable("")));
        assertFalse(SelectionValidation.idIsValid(Optional.ofNullable(" ")));
        assertFalse(SelectionValidation.idIsValid(Optional.ofNullable(null)));
        assertFalse(SelectionValidation.idIsValid(Optional.ofNullable("a")));
        assertFalse(SelectionValidation.idIsValid(Optional.ofNullable("-7")));
    }
}
