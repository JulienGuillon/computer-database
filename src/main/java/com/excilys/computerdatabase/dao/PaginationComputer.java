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
    private String name = "";
    private CrudComputer crudComputer;

    private ResultSet resultSet;

    private static final String PAGINATION_COMPUTERS = "select computer.id, computer.name, introduced, discontinued, company_id, company.name company_name from computer left join company on company.id = computer.company_id where computer.name like ? limit ? offset ?;";

    public PaginationComputer() {
        crudComputer = CrudComputerImpl.INSTANCE;
        this.numberOfComputers = crudComputer.getNumber();
        this.numberOfPages = numberOfComputers / size;
    }
    
    public List<Computer> getPageNumber(int pageNumber) {
        List<Computer> computers = new ArrayList<>();
        this.pageIndex = pageNumber;
        int offset = pageIndex * size;
        computers = crudComputer.findUsingPagination(size, offset, name);        
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
        return numberOfComputers;
    }

}
