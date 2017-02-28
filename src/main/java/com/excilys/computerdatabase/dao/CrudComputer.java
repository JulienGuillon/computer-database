package com.excilys.computerdatabase.dao;

import java.util.List;
import java.util.Optional;

import com.excilys.computerdatabase.entities.Computer;

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
     * @param id :
     */
    void update(Optional<Computer> computer, long id);

    /**
     * @param offset
     * @param size
     * @param name
     * @return
     */
    Optional<List<Optional<Computer>>> findUsingPaginationFilterByName(int offset, int size, String name);

}
