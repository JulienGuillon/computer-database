package com.excilys.computerdatabase.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdatabase.entity.Company;
import com.excilys.computerdatabase.pagination.Pagination;
import com.excilys.computerdatabase.persistence.CrudCompanySpring;
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
    private CrudCompanySpring manager;
    
    /**
     *
     * @return all companies in an optional list of optional company
     */
    public Iterable<Company> findAll() {
        Iterable<Company> companies = new ArrayList<>();
        companies = manager.findAll();
        return companies;
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
     * @return an optional list of Company
     */
    public Optional<Company> find(long id) {
        Company company = manager.findById(id);
        return Optional.ofNullable(company);
    }
    
    public Pagination<Company> getPage(Pagination<Company> page) {
        PageRequest request =
                new PageRequest(page.getPage(), page.getElementsByPage(), Sort.Direction.ASC, "name");
            Page<Company> pageCompany = manager.findByNameContaining(page.getFilter(), request);
            page.setElements(pageCompany.getContent());
            page.setTotalElements(getNumber(page.getFilter()));
        return page;
    } 
}
