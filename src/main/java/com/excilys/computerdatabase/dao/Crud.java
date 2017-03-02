package com.excilys.computerdatabase.dao;

import java.util.List;
import java.util.Optional;

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
     * @return an Optional list of Optional
     */
    Optional<List<Optional<T>>> findUsingPagination();

    /**
     *
     * @return number of row in the database
     */
    int getNumber();
}
