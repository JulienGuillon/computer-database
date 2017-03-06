package com.excilys.computerdatabase.dao;

import java.util.List;
import java.util.Optional;

import com.excilys.computerdatabase.entities.Computer;

/**
 * @author Guillon Julien
 *
 *         20 févr. 2017
 *
 *         Contains definition of crud operation
 */
public interface Crud<T> {

    /**
     *
     * @param id :
     * @return <T>
     */
    Optional<T> find(long id);

    /**
     *
     * @return a ResultSet
     */
    Optional<List<Optional<T>>> findAll();

    /**
     *
     * @return number of row in the database
     */
    int getNumber(String filter);
    
    public List<T> findUsingPagination(int size, int offset, String name);
}
