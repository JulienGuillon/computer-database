package com.excilys.computerdatabase.dto.mapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.entity.Company;
import com.excilys.computerdatabase.entity.Computer;

/**
 * @author jlng
 *
 * 2017-03-01
 */
public class MapperComputerDto {

    /**
     *
     * @param computers :
     * @return a list of computer dto
     */
    public static Iterable<ComputerDTO> toComputersDTO(Iterable<Computer> computers) {
        List<ComputerDTO> computersDTO = new ArrayList<>();
        for (Computer computer : computers) {
            Optional<ComputerDTO> optional = toComputerDTO(Optional.ofNullable(computer));
            if (optional.isPresent()) {                
                computersDTO.add(optional.get());
            }
        }
        return computersDTO;
    }

    /**
     * 
     *
     * @param computer :
     * @return a computer dto
     */
    public static Optional<ComputerDTO> toComputerDTO(Optional<Computer> optionalComputer) {
        ComputerDTO computerDTO = new ComputerDTO();            
        if (optionalComputer.isPresent()) {
            Computer computer = optionalComputer.get();
            computerDTO.setId(computer.getId());
            computerDTO.setName(computer.getName());
            computerDTO.setIntroduced(computer.getIntroduced() == null ? "" : computer.getIntroduced().toString());
            computerDTO.setDiscontinued(computer.getDiscontinued() == null ? "" : computer.getDiscontinued().toString());
            computerDTO.setManufacturerName(computer.getManufacturer() == null ? "" : computer.getManufacturer().getName());
            computerDTO.setManufacturerId(computer.getManufacturer() == null ? 0 : computer.getManufacturer().getId());
        }
        return Optional.ofNullable(computerDTO);
    }

    /**
     * @param optionalComputer :
     * @return an optional computer
     */
    public static Optional<Computer> fromDTOComputer(Optional<ComputerDTO> optionalComputer) {
        if (optionalComputer.isPresent()) {
            ComputerDTO computerDTO = optionalComputer.get();
            Computer computer = new Computer.Builder().withId(computerDTO.getId())
                    .withName(computerDTO.getName())
                    .withIntroduced(StringUtils.isNotBlank(computerDTO.getIntroduced()) ? LocalDate.parse(computerDTO.getIntroduced()) : null)
                    .withDiscontinued(StringUtils.isNotBlank(computerDTO.getDiscontinued()) ? LocalDate.parse(computerDTO.getDiscontinued()) : null)
                    .withManufacturer(computerDTO.getManufacturerId() != 0 ? new Company.Builder().withId(computerDTO.getManufacturerId())
                            .withName(computerDTO.getManufacturerId() != 0 ? computerDTO.getManufacturerName() : "")
                            .build() : null)
                    .build();
            return Optional.ofNullable(computer);
        }
        return Optional.empty();
    }

}
