package com.excilys.computerdatabase.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.entity.Company;
import com.excilys.computerdatabase.pagination.Page;
import com.excilys.computerdatabase.persistence.CrudCompany;
import com.excilys.computerdatabase.service.ServiceCompany;

/**
 * @author jlng
 *
 * 2017-03-02
 */

@Service
public class ServiceCompanyImpl implements ServiceCompany {

    @Autowired
    private CrudCompany crudCompany;
    /**
     *
     * @return all companies in an optional list of optional company
     */
    public List<Company> findAll() {
        return crudCompany.findAll();
    }

    /**
     * @param filter :
     * @return number of row in database
     */
    public int getNumber(String filter) {
        return 0;//crudCompany.getNumber(filter);
    }

    /**
     *
     * @param id :
     * @return an optional list of Company
     */
    public Optional<Company> find(long id) {
        return crudCompany.find(id);
    }
    
    public Page<Company> getPage(Page<Company> page) {
    	return null;//crudCompany.getPage(page);
    } 
}
