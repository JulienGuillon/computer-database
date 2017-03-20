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
    List<T> findAll();

    /**
     *
     * @param filter :
     * @return number of row in the database
     */
    int getNumber(String filter);

    /**
     *
     * @param size : number of element to show by page
     * @param offset :
     * @param name : string used to filter result
     * @return a list of elements
     */
    List<T> findUsingPagination(int size, int offset, String name);
}
