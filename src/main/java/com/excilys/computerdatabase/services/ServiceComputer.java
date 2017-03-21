package com.excilys.computerdatabase.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.dao.CrudComputer;
import com.excilys.computerdatabase.dao.impl.CrudComputerImpl;
import com.excilys.computerdatabase.entities.Computer;

/**
 * @author jlng
 *
 * 2017-03-02
 */

@Service
public class ServiceComputer {

    @Autowired
    private CrudComputerImpl crudComputer;

    /**
     *
     * @param optionalComputer
     *            :
     */
    public void create(Optional<Computer> optionalComputer) {
        crudComputer.create(optionalComputer);
    }

    /**
     *
     * @return all computers in an optional list
     */
    public List<Computer> findAll() {
        return crudComputer.findAll();
    }

    /**
     *
     * @param id
     *            :
     */
    public void delete(long id) {
        crudComputer.delete(id);
    }

    /**
     *
     * @param optionalComputer
     *            :
     */
    public void update(Optional<Computer> optionalComputer) {
        crudComputer.update(optionalComputer);
    }

    /**
     * @param filter :
     * @return number of row in database
     */
    public int getNumber(String filter) {
        return crudComputer.getNumber(filter);
    }

    /**
     *
     * @param id
     *            :
     * @return a computer found using its id
     */
    public Optional<Computer> find(long id) {
        return crudComputer.find(id);
    }
}
