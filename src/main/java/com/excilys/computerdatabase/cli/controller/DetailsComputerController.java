package com.excilys.computerdatabase.cli.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerdatabase.cli.view.DetailsComputerView;
import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.exception.PersistenceException;
import com.excilys.computerdatabase.service.impl.ServiceComputerImpl;

/**
 * @author Guillon Julien
 *
 *         21 févr. 2017
 *
 *         Controller for the DetailsComputerView It is a singleton. Allows to
 *         catch event on view DetailsComputerView and make validation.
 *
 */
public enum DetailsComputerController {
    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(DetailsComputerController.class);

    private DetailsComputerView detailsComputerView;

    private ServiceComputerImpl serviceComputer = new ServiceComputerImpl();

    /**
     * .
     */
    DetailsComputerController() {
    }

    /**
     * @param optionalDetailsComputerView :
     */
    public void setDetailsComputerView(Optional<DetailsComputerView> optionalDetailsComputerView) {
        if (optionalDetailsComputerView.isPresent()) {
            this.detailsComputerView = optionalDetailsComputerView.get();
        }
    }

    /**
     * @param choice :
     * @throws PersistenceException
     */
    public void findComputerById(int choice) {
        Optional<Computer> computer = serviceComputer.find(choice);
        if (computer.isPresent()) {
            detailsComputerView.displayDetails(computer);
        }
    }

    /**
     * @param optionalOperation :
     * @param id :
     * @throws PersistenceException
     */
    public void makeOperation(Optional<String> optionalOperation, long id) {
        if (optionalOperation.isPresent()) {
            String operation = optionalOperation.get();
            switch (operation) {
            case "d":
                serviceComputer.delete(id);
                detailsComputerView.displayDeletion(id);
                break;
            case "u":
                break;
            default:
                LOGGER.info("Choice is not valid !");
                break;
            }
        }
    }

}
