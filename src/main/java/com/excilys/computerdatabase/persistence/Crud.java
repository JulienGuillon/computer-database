package com.excilys.computerdatabase.persistence;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.excilys.computerdatabase.pagination.Page;

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
     * @param page
     * @return
     * @throws SQLException 
     */
    Page<T> getPage(Page<T> page);
}
