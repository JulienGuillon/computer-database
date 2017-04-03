package com.excilys.computerdatabase.controller;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.computerdatabase.dto.mapper.MapperCompanyDto;
import com.excilys.computerdatabase.entity.Company;
import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.service.ServiceCompany;
import com.excilys.computerdatabase.service.ServiceComputer;
import com.excilys.computerdatabase.validation.DateValidation;

/**
 * @author Guillon Julien
 *
 * 24 mars 2017
 */

@Controller
@RequestMapping("/addComputer")
public class AddComputerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddComputerController.class);

    @Autowired
    private ServiceComputer serviceComputer;
    
    
    @Autowired
    private ServiceCompany serviceCompany;

    @RequestMapping(method = RequestMethod.GET)
    public String doGet(ModelMap model) {
        model.addAttribute("companies", MapperCompanyDto.fromCompaniesToCompaniesDto(serviceCompany.findAll()));    
        return "addComputer";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String doPost(@RequestParam Map<String,String> params, ModelMap model) {         
        String computerName = params.get("computerName");
        String introduced = params.get("introduced");
        String discontinued = params.get("discontinued");        
        int companyId = Integer.parseInt(params.get("companyId"));
        LocalDate dateIntroduced = null;
        Computer.Builder computerBuilder = new Computer.Builder().withName(computerName);
        if (DateValidation.formatIsValid(Optional.of(introduced))
                && StringUtils.isNotBlank(introduced)) {
            dateIntroduced = LocalDate.parse(introduced);
            computerBuilder.withIntroduced(dateIntroduced);
        }

        LocalDate dateDiscontinued = null;
        if (DateValidation.formatIsValid(Optional.of(discontinued))
                && StringUtils.isNotBlank(discontinued)) {
            if (DateValidation.dateIsValid(Optional.ofNullable(dateIntroduced),
                    Optional.ofNullable(LocalDate.parse(discontinued)))) {
                dateDiscontinued = LocalDate.parse(discontinued);
                computerBuilder.withDiscontinued(dateDiscontinued);
            }
        }

        Company company = null;

        if (companyId != 0) {
            company = new Company.Builder().withId(companyId).build();
            computerBuilder.withManufacturer(company);
        }
        serviceComputer.create(Optional.ofNullable(computerBuilder.build()));
        return "redirect:computerdatabase";
    }
}
