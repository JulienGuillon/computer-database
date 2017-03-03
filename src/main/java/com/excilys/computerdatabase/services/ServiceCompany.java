package com.excilys.computerdatabase.services;

import java.util.List;
import java.util.Optional;

import com.excilys.computerdatabase.dao.CrudCompany;
import com.excilys.computerdatabase.dao.impl.CrudCompanyImpl;
import com.excilys.computerdatabase.entities.Company;

/**
 * @author jlng
 *
 * 2017-03-02
 */
public enum ServiceCompany {
    INSTANCE;
    
    private CrudCompany crudCompany = CrudCompanyImpl.INSTANCE;

    /**
     *
     * @return all companies in an optional list of optional company
     */
    public Optional<List<Optional<Company>>> findAll() {
        return crudCompany.findAll();
    }

    /**
     *
     * @return number of row in database
     */
    public int getNumber() {
        return crudCompany.getNumber();
    }

    /**
     *
     * @param id :
     * @return an optional list of Company
     */
    public Optional<Company> find(long id) {
        return crudCompany.find(id);
    }
}
