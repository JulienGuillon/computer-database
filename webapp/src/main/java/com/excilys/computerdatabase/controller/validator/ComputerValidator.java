package com.excilys.computerdatabase.controller.validator;

/**
 * @author Guillon Julien
 *
 * 10 avr. 2017
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.service.ServiceCompany;

@Component
public class ComputerValidator implements Validator {

    Logger LOGGER = LoggerFactory.getLogger(ComputerValidator.class);

    @Override
    public boolean supports(Class<?> clazz) {
        return ComputerDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ComputerDTO computer = (ComputerDTO) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "cdb.computerForm.name");

    }

}