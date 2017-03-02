package com.excilys.computerdatabase.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.entities.Company;
import com.excilys.computerdatabase.entities.Computer;

/**
 * @author jlng
 *
 * 2017-03-01
 */
public class MapperOptional {

    public static List<ComputerDTO> optionalListOfComputersToListOfComputersDTO(
            Optional<List<Optional<Computer>>> optionalComputers) {
        List<ComputerDTO> computersDTO = new ArrayList<>();
        if (optionalComputers.isPresent()) {
            List<Optional<Computer>> computersOptional = optionalComputers.get();
            for (Optional<Computer> optionalComputer : computersOptional) {
                if (optionalComputer.isPresent()) {
                    computersDTO.add(computerToComputerDTO(optionalComputer.get()));
                }
            }
        }
        return computersDTO;
    }

    public static List<Company> optionalListOfCompaniesToListOfCompanies(
            Optional<List<Optional<Company>>> optionalCompanies) {
        List<Company> companies = new ArrayList<>();
        if (optionalCompanies.isPresent()) {
            List<Optional<Company>> companiesOptional = optionalCompanies.get();
            for (Optional<Company> optionalCompany : companiesOptional) {
                if (optionalCompany.isPresent()) {
                    companies.add(optionalCompany.get());
                }
            }
        }
        return companies;
    }

    public static ComputerDTO computerToComputerDTO(Computer computer) {
        ComputerDTO computerDTO = new ComputerDTO();
        computerDTO.setId(computer.getId());
        computerDTO.setName(computer.getName());
        computerDTO.setIntroduced(computer.getIntroduced());
        computerDTO.setDiscontinued(computer.getDiscontinued());
        computerDTO.setManufacturerName(computer.getManufacturer().getName());
        return computerDTO;
    }

}
