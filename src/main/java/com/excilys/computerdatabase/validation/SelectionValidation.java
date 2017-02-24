package com.excilys.computerdatabase.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Guillon Julien
 *
 *         23 févr. 2017
 */
public class SelectionValidation {

    private static final Logger LOGGER = LoggerFactory.getLogger(SelectionValidation.class);

    /**
     * Verify if user choice is valid.
     * @param choice string to check
     * @return boolean
     */
    public static boolean userChoiceIsValid(String choice) {
        if (choice.matches("[npqduNPQDU]")) {
            return true;
        }
        LOGGER.info("Choice is not valid !");
        return false;
    }

    /**
     * Verify if user selection on menu is valid.
     * @param selection string to check
     * @return boolean
     */
    public static boolean userSelectionIsValid(String selection) {
        if (selection.matches("[1-6qQ]")) {
            return true;
        }
        LOGGER.info("Selection is not valid, should be number 1-6 or q !");
        return false;
    }

    /**
     * Verify if string .
     * @param id int to check
     * @return boolean
     */
    public static boolean idIsValid(String id) {
        if (id.matches("[\\d]*")) {
            return true;
        }
        LOGGER.info("Id is not valid, should be number !");
        return false;
    }
}
