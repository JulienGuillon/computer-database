package com.excilys.computerdatabase.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.entity.Computer;

/**
 * @author Guillon Julien
 *
 * 5 avr. 2017
 */

@Repository
public interface CrudComputerSpring extends PagingAndSortingRepository<Computer, Long>{
    Page<Computer> findByNameContaining (String name, Pageable request);

    int countByNameContaining (String name);
    
    Computer findById(long id);
    

}
