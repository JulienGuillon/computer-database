package com.excilys.computerdatabase.service;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.dto.mapper.MapperComputerDto;
import com.excilys.computerdatabase.service.impl.ServiceComputerImpl;


@RestController
@RequestMapping(value="/")
public class ComputerController {

    @Resource
    private  ServiceComputerImpl serviceComputer;

    @RequestMapping(value="computers/{id}", method=RequestMethod.GET)
    public ComputerDTO getComputer(@PathVariable Long id){
        return MapperComputerDto.toComputerDTO(serviceComputer.find(id)).get();
    }
}