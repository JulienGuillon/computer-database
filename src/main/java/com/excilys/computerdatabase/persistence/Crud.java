package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
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
    Optional<T> find(Connection connection, long id) throws SQLException;

    /**
     *
     * @return a ResultSet
     */
    List<T> findAll(Connection connection) throws SQLException;

    /**
     *
     * @param filter :
     * @return number of row in the database
     */
    int getNumber(Connection connection, String filter) throws SQLException;

    /**
     *
     * @param page
     * @return
     * @throws SQLException 
     */
    Page<T> getPage(Connection connection, Page<T> page) throws SQLException;
}
