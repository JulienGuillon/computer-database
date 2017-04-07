package com.excilys.computerdatabase.controller;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.dto.mapper.MapperCompanyDto;
import com.excilys.computerdatabase.dto.mapper.MapperComputerDto;
import com.excilys.computerdatabase.service.ServiceCompany;
import com.excilys.computerdatabase.service.ServiceComputer;

/**
 * @author Guillon Julien
 *
 * 24 mars 2017
 * 
 */

@Controller
@RequestMapping("/addComputer")
public class AddComputerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddComputerController.class);

    @Autowired
    private ServiceComputer serviceComputer;
    
    
    @Autowired
    private ServiceCompany serviceCompany;
    
    @Autowired
    private Validator validator;
    
    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String doGet(ModelMap model) {
        model.addAttribute("companies", MapperCompanyDto.fromCompaniesToCompaniesDto(serviceCompany.findAll()));    
        model.addAttribute("computerForm", new ComputerDTO());
        return "addComputer";
    }
   
    @RequestMapping(method = RequestMethod.POST)
    public String doPost(@ModelAttribute("computerForm") ComputerDTO computerDto, BindingResult result, Model model, final RedirectAttributes redirectAttribute) {
        
        validator.validate(computerDto, result);
        LOGGER.info("result: " + result.hasErrors());

        if (result.hasErrors()) {
            LOGGER.info("result errors");
            return "addComputer";
        }
            serviceComputer.create(MapperComputerDto.fromDTOComputer(Optional.ofNullable(computerDto)));
            return "redirect:computerdatabase";
    }
    
}


