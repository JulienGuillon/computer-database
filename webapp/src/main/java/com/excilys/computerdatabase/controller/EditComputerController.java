package com.excilys.computerdatabase.controller;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.excilys.computerdatabase.controller.validator.ComputerValidator;
import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.dto.mapper.MapperCompanyDto;
import com.excilys.computerdatabase.dto.mapper.MapperComputerDto;
import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.service.ServiceCompany;
import com.excilys.computerdatabase.service.ServiceComputer;

/**
 * @author Guillon Julien
 *
 * 23 mars 2017
 */

@Controller
@RequestMapping("/editComputer")
public class EditComputerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EditComputerController.class);

    @Autowired
    private ServiceComputer serviceComputer;
    
    @Autowired
    private ServiceCompany serviceCompany;
    
    private Optional<ComputerDTO> optionalComputer = Optional.empty(); 
    
    @Autowired
    private ComputerValidator computerFormValidator;
    
    @InitBinder("ComputerDto")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(computerFormValidator);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String doGet(Model model, @RequestParam Map<String, String> params) {
        
        model.addAttribute("computerEdit", new ComputerDTO());
        setParamToJsp(model,  Integer.parseInt(params.get("id")));
        return "editComputer";
    }
 
    /**
     * 
     */
    private void setParamToJsp(Model model, long id) {
        optionalComputer = MapperComputerDto.toComputerDTO(serviceComputer.find(id));
        model.addAttribute("computer", optionalComputer.get());
        model.addAttribute("companies", MapperCompanyDto.fromCompaniesToCompaniesDto(serviceCompany.findAll()));    
    }


    @RequestMapping(method = RequestMethod.POST)
    public String doPost(@ModelAttribute("computerEdit") @Valid ComputerDTO computerDto, BindingResult result, Model model, final RedirectAttributes redirectAttribute) {
    	 computerFormValidator.validate(computerDto, result);
         if (result.hasErrors()) {
             LOGGER.info("result errors");
             setParamToJsp(model, computerDto.getId());
             return "editComputer";
         }
        computerDto = updateComputer(computerDto);
        serviceComputer.update(MapperComputerDto.fromDTOComputer(Optional.ofNullable(computerDto)));
        return "redirect:computerdatabase";
    }
   
    public ComputerDTO updateComputer(ComputerDTO computerDto) {
        ComputerDTO computer = optionalComputer.get();
        computer.setId(computerDto.getId());
        computer.setName(computerDto.getName());
        computer.setIntroduced(StringUtils.isBlank(computerDto.getIntroduced()) ? computer.getIntroduced() : computerDto.getIntroduced());
        computer.setDiscontinued(StringUtils.isBlank(computerDto.getDiscontinued()) ? computer.getDiscontinued() : computerDto.getDiscontinued());
        computer.setManufacturerName(computerDto.getManufacturerId() == 0 ? computer.getManufacturerName() : computerDto.getManufacturerName());        
        computer.setManufacturerId(computerDto.getId() == 0 ? computer.getManufacturerId() : computerDto.getManufacturerId());        
        return computer;
    }

}