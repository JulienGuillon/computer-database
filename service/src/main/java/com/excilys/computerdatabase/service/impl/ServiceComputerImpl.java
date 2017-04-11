package com.excilys.computerdatabase.service.impl;

import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.pagination.Pagination;
import com.excilys.computerdatabase.persistence.CrudComputerSpring;
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
    private CrudComputerSpring manager;

    /**
     *
     * @param optionalComputer :
     *
     */
    public void create(Optional<Computer> optionalComputer) {
        manager.save(optionalComputer.get());
    }

    /**
     *
     * @return all computers in an optional list
     */
    public Iterable<Computer> findAll() {
        Iterable<Computer> computers = new ArrayList<>();
        computers =  manager.findAll();
        return computers;
    }

    /**
     *
     * @param id :
     *
     */
    public void delete(long id) {
        manager.delete(id);
    }

    /**
     *
     * @param optionalComputer :
     *
     */
    public void update(Optional<Computer> optionalComputer) {
        manager.save(optionalComputer.get());
    }

    /**
     * @param filter :
     * @return number of row in database
     */
    public int getNumber(String filter) {
       
        int number = -1;
        number = manager.countByNameContaining(filter);
        return number;
    }

    /**
     *
     * @param id :
     * @return a computer found using its id
     */
    public Optional<Computer> find(long id) {
        Computer computer = manager.findById(id);
        return Optional.ofNullable(computer);
    }

    /**
     *
     */ 
    public Pagination<Computer> getPage(Pagination<Computer> page) {
        PageRequest request =
                new PageRequest(page.getPage(), page.getElementsByPage(), Sort.Direction.ASC, "name");
            Page<Computer> pageComputer = manager.findByNameContaining(page.getFilter(), request);
            page.setElements(pageComputer.getContent());
            page.setTotalElements(getNumber(page.getFilter()));
            return page;
    }

    /**
     * @param selection
     */
    public void multipleDelete(String selection) {
        //manager.deleteComputers(selection);
    }
}
