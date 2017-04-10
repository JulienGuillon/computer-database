package com.excilys.computerdatabase.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.dto.mapper.MapperComputerDto;
import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.pagination.Pagination;
import com.excilys.computerdatabase.service.ServiceComputer;

/**
 * @author Guillon Julien
 *
 * 23 mars 2017
 */

@Controller
@RequestMapping("/computerdatabase")
public class DashboardController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardController.class);

    @Autowired
    private ServiceComputer serviceComputer;

    @RequestMapping(method = RequestMethod.GET)
    public String doGet(ModelMap model, @RequestParam Map<String, String> params) {
        Pagination<Computer> page = new Pagination<>();

        page.setPage(params.get("numOfPage") != null ? Integer.parseInt(params.get("numOfPage")) : 0);
        page.setFilter(params.get("filter") != null ? params.get("filter") : "");
        page.setElementsByPage(params.get("limit") != null ? Integer.parseInt(params.get("limit")) : 10);
        
        setParamToJsp(model, page);
        return "dashboard";
    }
 
    /**
     * 
     */
    private void setParamToJsp(ModelMap model, Pagination<Computer> page) {
        page = serviceComputer.getPage(page);
        List<ComputerDTO> computers = MapperComputerDto.toComputersDTO(page.getElements());
        model.addAttribute("numberOfComputers", page.getTotalElements());        
        model.addAttribute("computers", computers);
        model.addAttribute("limit", page.getElementsByPage());
        model.addAttribute("currentPage", page.getPage());
        model.addAttribute("filter", page.getFilter());
        model.addAttribute("numberOfPages", page.getTotalPages());

    }

    @RequestMapping(method = RequestMethod.POST)
    public String doPost(ModelMap model, 
            @RequestParam(value = "selection") String selection) {
        serviceComputer.multipleDelete(selection);
        LOGGER.info(selection);
        
       return "redirect:computerdatabase";
    }
 
}