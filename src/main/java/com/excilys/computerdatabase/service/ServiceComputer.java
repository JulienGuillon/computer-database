package com.excilys.computerdatabase.service;

import java.util.Optional;

import com.excilys.computerdatabase.entity.Computer;

/**
 * @author Guillon Julien
 *
 * 22 mars 2017
 */
public interface ServiceComputer extends ServiceBase<Computer> {
    
    public void create(Optional<Computer> optionalComputer);
    
    public void delete(long id);

    public void update(Optional<Computer> optionalComputer);
}
