package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import com.excilys.computerdatabase.entity.Computer;

/**
 * @author Guillon Julien
 *
 *         24 févr. 2017
 */

public interface CrudComputer extends Crud<Computer> {
    /**
     *
     * @param computer :
     * @throws SQLException 
     */
    void create(Connection connection, Optional<Computer> computer) throws SQLException;

    /**
     *
     * @param id :
     */
    void delete(long id);

    /**
     *
     * @param computer :
     */
    void update(Connection connection, Optional<Computer> computer) throws SQLException;

    /**
     * @param selection
     */
    void multipleDelete(Connection connection, String selection) throws SQLException;
}
