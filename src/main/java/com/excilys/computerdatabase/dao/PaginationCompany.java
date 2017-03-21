package com.excilys.computerdatabase.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.dao.impl.CrudCompanyImpl;
import com.excilys.computerdatabase.entities.Company;

/**
 * @author Guillon Julien
 *
 * 3 mars 2017
 */

@Repository
public class PaginationCompany {

    private int size = 10;
    private int pageIndex = 0;
    private int numberOfPages;
    private int numberOfCompanie;
    private String filter = "";
    
    @Autowired
    private CrudCompanyImpl crudCompany;


    public PaginationCompany() {
    }
    
    public void init() {
        this.numberOfCompanie = crudCompany.getNumber(filter);
        this.numberOfPages = numberOfCompanie / size;
    }

    public List<Company> getPage(int pageNumber) {
        List<Company> companies = new ArrayList<>();
        this.pageIndex = pageNumber;
        int offset = pageIndex * size;
        companies = crudCompany.findUsingPagination(size, offset, filter);
        return companies;
    }

    public void setSize(int size) {
        this.size = size;
        this.pageIndex = 0;
        this.numberOfPages = this.numberOfCompanie / this.size;
    }

    /**
     *
     */
    public List<Company> nextPage() {
        this.pageIndex = pageIndex + 1;     
        return getPage(pageIndex);
    }

    /**
     *
     */
    public List<Company> previousPage() {
        this.pageIndex = (pageIndex - 1) >= 0 ? (pageIndex - 1) * size : 0;
        return getPage(pageIndex);
    }

}
