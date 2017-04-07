package com.excilys.computerdatabase.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.pagination.Pagination;

/**
 * @author Guillon Julien
 *
 * 22 mars 2017
 */
public interface ServiceComputer extends ServiceBase<Computer> {
    
    public void create(Optional<Computer> optionalComputer);
    
    public void delete(long id);

    public void update(Optional<Computer> optionalComputer);

    /**
     * @param selection
     */
    public void multipleDelete(String selection);
    
}
