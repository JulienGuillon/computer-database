package com.excilys.computerdatabase.validations;

import java.util.Optional;

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
     *
     * @param optionalChoice :
     * @return a boolean
     */
    public static boolean userChoiceIsValid(Optional<String> optionalChoice) {
        if (optionalChoice.isPresent()) {
            String choice = optionalChoice.get();
            if (choice.matches("[npqduNPQDU]")) {
                return true;
            }
            LOGGER.info("Choice is not valid !");
        }
        return false;
    }

    /**
     *
     * @param optionalSelection :
     * @return a boolean
     */
    public static boolean userSelectionIsValid(Optional<String> optionalSelection) {
        if (optionalSelection.isPresent()) {
            String selection = optionalSelection.get();
            if (selection.matches("[1-6qQ]")) {
                return true;
            }
            LOGGER.info("Selection is not valid, should be number 1-6 or q !");
        }
        return false;
    }

    /**
     *
     * @param optionalId :
     * @return a boolean
     */
    public static boolean idIsValid(Optional<String> optionalId) {
        if (optionalId.isPresent()) {
            String id = optionalId.get();
            if (id.matches("[\\d]+")) {
                return true;
            }
            LOGGER.info("Id is not valid, should be number !");
        }
        return false;
    }
}
