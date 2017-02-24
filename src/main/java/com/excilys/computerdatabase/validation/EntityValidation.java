package com.excilys.computerdatabase.validation;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Guillon Julien
 *
 *         24 févr. 2017
 */
public class EntityValidation {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntityValidation.class);

    /**
     * Verify if a name is valid for entities.
     * @param name string to verify
     * @return boolean
     */
    public static boolean nameIsValid(String name) {
        if (!StringUtils.isBlank(name) && name.matches("[\\w-\\s.]*")) {
            return true;
        }
        LOGGER.info("Name is not valid, contains characters not permitted or is blank!");
        return false;
    }
}
