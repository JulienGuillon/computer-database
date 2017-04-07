package com.excilys.computerdatabase.cli.controller;

import java.sql.SQLException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.computerdatabase.cli.view.ListComputerView;
import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.exception.PersistenceException;
import com.excilys.computerdatabase.pagination.Pagination;
import com.excilys.computerdatabase.service.ServiceComputer;
import com.excilys.computerdatabase.service.impl.ServiceComputerImpl;
import com.excilys.computerdatabase.springConfig.CdbConfiguration;

/**
 * @author Guillon Julien
 *
 *         21 févr. 2017
 *
 *         Controller for the ListComputerView It is a singleton. Allows to
 *         catch event on view ListComputerView and make validation,
 *
 */
public enum ListComputerController {
    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(ListComputerController.class);
    private ListComputerView listComputerView;

    private int offset;

    private ApplicationContext context = new AnnotationConfigApplicationContext(CdbConfiguration.class);
    
    private ServiceComputer serviceComputer = context.getBean(ServiceComputerImpl.class);

    private Pagination<Computer> page = new Pagination();

    /**
     *
     */
    ListComputerController() {
    }

    /**
     * Find all computers with pagination by using crud method and call to.
     * update view
     *
     * @param optionalChoice : could be "n" or "p" to switch of page
     * @throws PersistenceException
     * @throws SQLException
     *
     */
    public void findComputers(Optional<String> optionalChoice) {
        if (optionalChoice.isPresent()) {
            boolean quit = false;
            String choice = optionalChoice.get();
            switch (choice) {
            case "n":
                offset = (offset + 1);
                break;
            case "p":
                offset = ((offset - 1) >= 0) ? (offset - 1) : -1;
                break;
            case "q":
                offset = -1;
                quit = true;
                break;
            default:
                LOGGER.info("Choice is not valid !");
                break;
            }
            if (!quit && offset >= 0) {
            	page.setPage(offset);
                //page = serviceComputer.getPage(page);
                listComputerView.displayComputers(page.getElements());
            }
        }
    }

    /**
     * @param optionalListComputerView :
     * the listComputerView to set
     */
    public void setListComputerView(Optional<ListComputerView> optionalListComputerView) {
        if (optionalListComputerView.isPresent()) {
            this.listComputerView = optionalListComputerView.get();
        }
    }

}
