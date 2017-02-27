package com.excilys.computerdatabase.dao;

import java.sql.ResultSet;
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
    Optional<ResultSet> findAll();

    /**
     *
     * @param offset :
     * @return an Optional list of Optional
     */
    Optional<List<Optional<T>>> findUsingPagination(int offset);

    /**
     *
     * @param offset :
     * @param size :
     * @return an Optional list of Optional
     */
    Optional<List<Optional<T>>> findUsingPagination(int offset, int size);
}
