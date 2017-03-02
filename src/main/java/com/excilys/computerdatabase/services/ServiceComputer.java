package com.excilys.computerdatabase.services;

import java.util.List;
import java.util.Optional;

import com.excilys.computerdatabase.dao.CrudComputer;
import com.excilys.computerdatabase.dao.impl.CrudComputerImpl;
import com.excilys.computerdatabase.entities.Computer;

/**
 * @author jlng
 *
 * 2017-03-02
 */
public class ServiceComputer {

    private static CrudComputer crudComputer = CrudComputerImpl.INSTANCE;

    /**
     *
     * @param optionalComputer
     *            :
     */
    public static void create(Optional<Computer> optionalComputer) {
        crudComputer.create(optionalComputer);
    }

    /**
     *
     * @return all computers in an optional list
     */
    public static Optional<List<Optional<Computer>>> findAll() {
        return crudComputer.findAll();
    }

    /**
     *
     * @return paginated computers in an optional list of optional computer
     */
    public static Optional<List<Optional<Computer>>> findUsingPagination() {
        return crudComputer.findUsingPagination();
    }

    /**
     *
     * @param id
     *            :
     */
    public static void delete(long id) {
        crudComputer.delete(id);
    }

    /**
     *
     * @param optionalComputer
     *            :
     */
    public static void update(Optional<Computer> optionalComputer) {
        crudComputer.update(optionalComputer);
    }

    /**
     *
     * @return number of row in database
     */
    public static int getNumber() {
        return crudComputer.getNumber();
    }

    /**
     *
     * @param id
     *            :
     * @return a computer found using its id
     */
    public static Optional<Computer> find(long id) {
        return crudComputer.find(id);
    }
}
