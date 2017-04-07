package com.excilys.computerdatabase.controller;

import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    
    private Optional<ComputerDTO> optionalComputer; 
    
    @RequestMapping(method = RequestMethod.GET)
    public String doGet(ModelMap model, @RequestParam Map<String, String> params) {
        
        model.addAttribute("computerEdit", new ComputerDTO());
        setParamToJsp(model, Integer.parseInt(params.get("id")));
        return "editComputer";
    }
 
    /**
     * 
     */
    private void setParamToJsp(ModelMap model, int id) {
        optionalComputer = MapperComputerDto.toComputerDTO(serviceComputer.find(id));
        model.addAttribute("computer", optionalComputer.get());
        model.addAttribute("companies", MapperCompanyDto.fromCompaniesToCompaniesDto(serviceCompany.findAll()));    
    }


    @RequestMapping(method = RequestMethod.POST)
    public String doPost(@ModelAttribute("computerEdit") ComputerDTO computerDto, ModelMap model) {
        computerDto = updateComputer(computerDto);
        serviceComputer.update(MapperComputerDto.fromDTOComputer(Optional.ofNullable(computerDto)));
        return "redirect:computerdatabase";
    }
   
    public ComputerDTO updateComputer(ComputerDTO computerDto) {
        ComputerDTO computer = optionalComputer.get();
        computer.setName(computerDto.getName());
        computer.setIntroduced(StringUtils.isBlank(computerDto.getIntroduced()) ? computer.getIntroduced() : computerDto.getIntroduced());
        computer.setDiscontinued(StringUtils.isBlank(computerDto.getDiscontinued()) ? computer.getDiscontinued() : computerDto.getDiscontinued());
        computer.setManufacturerName(computerDto.getManufacturerId() == 0 ? computer.getManufacturerName() : computerDto.getManufacturerName());        
        computer.setManufacturerId(computerDto.getId() == 0 ? computer.getManufacturerId() : computerDto.getManufacturerId());        
        return computer;
    }

}