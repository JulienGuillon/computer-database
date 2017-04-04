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
     */
    void create(Optional<Computer> computer);

    /**
     *
     * @param id :
     */
    void delete(long id);

    /**
     *
     * @param computer :
     */
    void update(Optional<Computer> computer);

    /**
     * @param selection
     */
    void multipleDelete(String selection);
}
