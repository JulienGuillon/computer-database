package com.excilys.computerdatabase.service;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.dto.mapper.MapperComputerDto;


@RestController
@RequestMapping(value="/computers/")
@Produces("application/json")
public class ComputerController {

    @Resource
    private  ServiceComputer serviceComputer;

    @RequestMapping(value="{id}", method=RequestMethod.GET)
    public ComputerDTO getComputer(@PathVariable Long id){
        return MapperComputerDto.toComputerDTO(serviceComputer.find(id)).get();
    }
    
    @RequestMapping(value="", method=RequestMethod.GET)
    public Iterable<ComputerDTO> getComputers(){
        return MapperComputerDto.toComputersDTO(serviceComputer.findAll());
    }
    
    @RequestMapping(value="update/", method=RequestMethod.POST)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateComputer(ComputerDTO computer) { 
        return Response.status(201).entity(computer).build(); 
    }
}