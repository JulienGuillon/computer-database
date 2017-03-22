package com.excilys.computerdatabase.cli.controller;

import java.util.Optional;

import com.excilys.computerdatabase.cli.view.UpdateComputerView;
import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.exception.PersistenceException;
import com.excilys.computerdatabase.service.impl.ServiceComputerImpl;

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

    private ServiceComputerImpl serviceComputer = new ServiceComputerImpl();

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
