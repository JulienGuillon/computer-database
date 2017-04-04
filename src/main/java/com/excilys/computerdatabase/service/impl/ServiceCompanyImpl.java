package com.excilys.computerdatabase.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdatabase.entity.Company;
import com.excilys.computerdatabase.exception.PersistenceException;
import com.excilys.computerdatabase.pagination.Page;
import com.excilys.computerdatabase.persistence.CrudCompany;
import com.excilys.computerdatabase.persistence.DatabaseManager;
import com.excilys.computerdatabase.service.ServiceCompany;

/**
 * @author jlng
 *
 * 2017-03-02
 */

@Service
@Transactional
public class ServiceCompanyImpl implements ServiceCompany {

    @Autowired
    private CrudCompany crudCompany;
    
    @Autowired
    private DatabaseManager databaseManager;
    
    /**
     *
     * @return all companies in an optional list of optional company
     */
    public List<Company> findAll() {
        List<Company> companies = new ArrayList<>();
        companies = crudCompany.findAll();
        return companies;
    }

    /**
     * @param filter :
     * @return number of row in database
     */
    public int getNumber(String filter) {
        int number = -1;
        number = crudCompany.getNumber(filter);
        return number;
    }

    /**
     *
     * @param id :
     * @return an optional list of Company
     */
    public Optional<Company> find(long id) {
        Optional<Company> company = Optional.empty();
        company = crudCompany.find(id);
        return company;
    }
    
    public Page<Company> getPage(Page<Company> page) {
        page = crudCompany.getPage(page);
        return page;
    } 
}
