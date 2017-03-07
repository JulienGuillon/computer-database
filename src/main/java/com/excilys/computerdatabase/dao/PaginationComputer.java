package com.excilys.computerdatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.computerdatabase.dao.impl.CrudComputerImpl;
import com.excilys.computerdatabase.dao.mapper.MapperComputer;
import com.excilys.computerdatabase.entities.Computer;
import com.excilys.computerdatabase.exceptions.PersistenceException;

/**
 * @author Guillon Julien
 *
 * 3 mars 2017
 */
public class PaginationComputer {

    private int size = 10;
    private int pageIndex = 0;
    private int numberOfPages;
    private int numberOfComputers;
    private String filter = "";
    private CrudComputer crudComputer;

    public PaginationComputer() {
        crudComputer = CrudComputerImpl.INSTANCE;
        this.numberOfComputers = crudComputer.getNumber(filter);
        this.numberOfPages = numberOfComputers / size;
    }
    
    public List<Computer> getPageNumber(int pageNumber) {
        List<Computer> computers = new ArrayList<>();
        this.pageIndex = pageNumber;
        int offset = pageIndex * size;
        computers = crudComputer.findUsingPagination(size, offset, filter);        
        return computers;
    }
    
    
    public void setSize(int size) {
        this.size = size;
        this.pageIndex = 0;
        this.numberOfPages = this.numberOfComputers / this.size;
    }

    /**
     *
     */
    public List<Computer> nextPage() {
        this.pageIndex = pageIndex + 1;
        return getPageNumber(pageIndex);
    }

    /**
     *
     */
    public List<Computer> previousPage() {
        this.pageIndex = (pageIndex - 1) >= 0 ? (pageIndex - 1) * size : 0;
        return getPageNumber(pageIndex);

    }

    /**
     * @return
     */
    public int getNumberOfPages() {
        return numberOfPages;
    }

    /**
     * @return
     */
    public int getPageIndex() {
        return pageIndex;
    }

    /**
     * @return
     */
    public int getNumberOfComputers() {
        this.numberOfComputers = crudComputer.getNumber(filter);
        return numberOfComputers;
    }

    /**
     * @return
     */
    public int getSize() {
        return size;
    }
    
    /**
     * @return the filter
     */
    public String getFilter() {
        return filter;
    }
    
    /**
     * @param filter the filter to set
     */
    public void setFilter(String filter) {
        this.filter = filter;
    }

}
