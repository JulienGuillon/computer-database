package com.excilys.computerdatabase.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.computerdatabase.dao.CrudComputer;
import com.excilys.computerdatabase.dao.impl.CrudComputerImpl;
import com.excilys.computerdatabase.entities.Computer;

/**
 * @author Guillon Julien
 *
 * 28 févr. 2017
 */
public class ManagerBeanComputer {

    private CrudComputer crudComputer = CrudComputerImpl.INSTANCE;
    
    private int currentPage = 0;
    private int sizePage = 100;
    private int numberOfComputers;
    private String filter = "";
    private Computer computer;
    
    public int getNumberOfComputer() {
        numberOfComputers = crudComputer.getNumber();
        return numberOfComputers;
    }
    
    public int getNumberOfPages() {
        return numberOfComputers / sizePage;
    }
    
    public List<Computer> getComputersByPage() {
        List<Computer> computers = new ArrayList<>();
        Optional<List<Optional<Computer>>> optionalListOfComputer = crudComputer.findUsingPaginationFilterByName(currentPage*sizePage, sizePage, filter);
        if (optionalListOfComputer.isPresent()) {
            List<Optional<Computer>> optionalComputers = optionalListOfComputer.get();
            for (Optional<Computer> optionalComputer : optionalComputers) {
                if(optionalComputer.isPresent()) {
                    computers.add(optionalComputer.get());
                }
            }
        }
        return computers;
    }
    
     /**
     * @return the sizePage
     */
    public int getSizePage() {
        return sizePage;
    }
    
    /**
     * @return the currentPage
     */
    public int getCurrentPage() {
        return currentPage;
    }
    
    /**
     * @param sizePage the sizePage to set
     */
    public void setSizePage(int sizePage) {
        this.sizePage = sizePage;
    }
    
    /**
     * @param currentPage the currentPage to set
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
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
    /**
     * @return the computer
     */
    public Computer getComputer() {
        return computer;
    }
    
    /**
     * @param computer the computer to set
     */
    public void setComputer(Computer computer) {
        System.out.println(computer.getName());
        this.computer = computer;
    }
    
    
}
