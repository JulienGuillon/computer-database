package com.excilys.computerdatabase.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.dto.mapper.MapperComputerDto;
import com.excilys.computerdatabase.service.ServiceComputer;


@RestController
@RequestMapping(value="/")
public class ComputerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerController.class);

    @Resource
    private  ServiceComputer serviceComputer;

    @RequestMapping(value="computers/{id}", method=RequestMethod.GET)
    public ComputerDTO getComputer(@PathVariable Long id){
        ComputerDTO computer = MapperComputerDto.toComputerDTO(serviceComputer.find(id)).get();
        return computer;
    }
    
}