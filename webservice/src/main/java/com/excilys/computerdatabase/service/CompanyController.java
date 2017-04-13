package com.excilys.computerdatabase.service;

import javax.annotation.Resource;
import javax.ws.rs.Produces;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.computerdatabase.dto.CompanyDTO;
import com.excilys.computerdatabase.dto.mapper.MapperCompanyDto;


@RestController
@RequestMapping(value="/companies/")
@Produces("application/json")
public class CompanyController {

    @Resource
    private  ServiceCompany serviceCompany;

    @RequestMapping(value="{id}", method=RequestMethod.GET)
    public CompanyDTO getCompany(@PathVariable Long id){
        return MapperCompanyDto.toDTO(serviceCompany.find(id).get());
    }
    
    @RequestMapping(value="", method=RequestMethod.GET)
    public Iterable<CompanyDTO> getCompanies(){
        return MapperCompanyDto.fromCompaniesToCompaniesDto(serviceCompany.findAll());
    }
    
}