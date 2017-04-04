package com.excilys.computerdatabase.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.pagination.Page;
import com.excilys.computerdatabase.persistence.CrudComputer;
import com.excilys.computerdatabase.persistence.DatabaseManager;
import com.excilys.computerdatabase.service.ServiceComputer;

/**
 * @author jlng
 *
 * 2017-03-02
 */

@Service
@Transactional
public class ServiceComputerImpl implements ServiceComputer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceComputerImpl.class);

    @Autowired
    private CrudComputer crudComputer;
    
    @Autowired
    private DatabaseManager databaseManager;

    /**
     *
     * @param optionalComputer :
     *
     */
    public void create(Optional<Computer> optionalComputer) {
        crudComputer.create(optionalComputer);
    }

    /**
     *
     * @return all computers in an optional list
     */
    public List<Computer> findAll() {
        List<Computer> computers = new ArrayList();
        computers =  crudComputer.findAll();
        return computers;
    }

    /**
     *
     * @param id :
     *
     */
    public void delete(long id) {
        crudComputer.delete(id);
    }

    /**
     *
     * @param optionalComputer :
     *
     */
    public void update(Optional<Computer> optionalComputer) {
        crudComputer.update(optionalComputer);
    }

    /**
     * @param filter :
     * @return number of row in database
     */
    public int getNumber(String filter) {
        int number = -1;
        number = crudComputer.getNumber(filter);
        return number;
    }

    /**
     *
     * @param id :
     * @return a computer found using its id
     */
    public Optional<Computer> find(long id) {
        Optional<Computer> computer = Optional.empty();
        computer = crudComputer.find(id);
        return computer;
    }

    /**
     *
     */
    public Page<Computer> getPage(Page<Computer> page) {
        
        page = crudComputer.getPage(page);
        return page;
    }

    /**
     * @param selection
     */
    public void multipleDelete(String selection) {
        crudComputer.multipleDelete(selection);
    }
}
