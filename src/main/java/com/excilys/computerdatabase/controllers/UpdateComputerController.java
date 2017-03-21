package com.excilys.computerdatabase.controllers;

import java.util.Optional;

import com.excilys.computerdatabase.entities.Computer;
import com.excilys.computerdatabase.exceptions.PersistenceException;
import com.excilys.computerdatabase.services.ServiceComputer;
import com.excilys.computerdatabase.views.UpdateComputerView;

/**
 * @author Guillon Julien
 *
 *         21 févr. 2017
 *
 *         Controler for the UpdateComputerView It is a singleton. Allows to
 *         catch event on view UpdateComputerView and make validation.
 *
 */
public enum UpdateComputerController {
    INSTANCE;

    private UpdateComputerView updateComputerView;

    private ServiceComputer serviceComputer = new ServiceComputer();

    /**
     *
     */
    UpdateComputerController() {
    }

    /**
     * @param optionalUpdateComputerView :
     */
    public void setUpdateComputerView(Optional<UpdateComputerView> optionalUpdateComputerView) {
        if (optionalUpdateComputerView.isPresent()) {
            this.updateComputerView = optionalUpdateComputerView.get();
        }
    }

    /**
     * Find a computer by id by using crud method and call to update view to.
     * display details of computer
     *
     * @param choice :
     * @throws PersistenceException
     * @throws Exception
     */
    public void findComputerById(int choice) {
        Optional<Computer> computer = serviceComputer.find(choice);
        if (computer.isPresent()) {
            updateComputerView.displayDetailsToUpdate(computer);
        }
    }

    /**
     * Update a computer by using crud method and call to update view to display MainMenu.
     *
     * @param optionalComputer :
     * @throws PersistenceException
     * @throws Exception
     */
    public void update(Optional<Computer> optionalComputer) {
        if (optionalComputer.isPresent()) {
            Computer computer = optionalComputer.get();
            serviceComputer.update(Optional.ofNullable(computer));
            updateComputerView.displayInfoComputer(Optional.ofNullable(computer));
        }
    }

}
