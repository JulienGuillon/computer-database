package com.excilys.computerdatabase.validation;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Guillon Julien
 *
 * 24 févr. 2017
 */
public class EntityValidation {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntityValidation.class);

    /**
     *
     * @param optionalName :
     * @return a boolean
     */
    public static boolean nameIsValid(Optional<String> optionalName) {
        if (optionalName.isPresent()) {
            String name = optionalName.get();
            if (StringUtils.isNotBlank(name) && name.matches("[\\w-_.\\s]*")) {
                return true;
            }
            LOGGER.info("Name is not valid, contains characters not permitted or is blank!");
        }
        return false;
    }
}
