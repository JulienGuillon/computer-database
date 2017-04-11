package com.excilys.computerdatabase.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.entity.Company;

/**
 * @author Guillon Julien
 *
 * 5 avr. 2017
 */

@Repository
public interface CrudCompanySpring extends PagingAndSortingRepository<Company, Long>{

    Page<Company> findByNameContaining (String name, Pageable request);

    int countByNameContaining (String name);
    
    Company findById(long id);
}
