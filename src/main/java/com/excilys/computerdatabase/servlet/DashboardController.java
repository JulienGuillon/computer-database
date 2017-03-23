package com.excilys.computerdatabase.servlet;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.dto.mapper.MapperComputerDto;
import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.pagination.Page;
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
    public String doGet(ModelMap model,
            @RequestParam(value = "limit", defaultValue="10") int limit,
            @RequestParam(value = "numOfPage", defaultValue="0") int numOfPage,
            @RequestParam(value = "filter", defaultValue="") String filter) {

        Page<Computer> page = new Page<>();
        page.setPage(numOfPage);
        page.setFilter(filter);
        page.setElementsByPage(limit);
        
        setParamToJsp(model, page);
        return "dashboard";
    }
 
    /**
     * 
     */
    private void setParamToJsp(ModelMap model, Page<Computer> page) {
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