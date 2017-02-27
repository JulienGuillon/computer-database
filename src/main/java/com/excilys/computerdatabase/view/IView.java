package com.excilys.computerdatabase.view;

import com.excilys.computerdatabase.exception.PersistenceException;

/**
 * @author Guillon Julien
 *
 * 21 févr. 2017
 *
 * Allows to select a view to display
 *
 */
public interface IView {
    /**
     *
     * @throws PersistenceException :
     */
    static void displayMainMenu() throws PersistenceException {
        MainMenuView.INSTANCE.displayUI();
    }

    /**
     *
     * @throws PersistenceException :
     */
    static void displayComputers() throws PersistenceException {
        ListComputerView.INSTANCE.displayUI();
    }

    /**
     *
     * @throws PersistenceException :
     */
    static void displayCompanies() throws PersistenceException {
        ListCompanyView.INSTANCE.displayUI();
    }

    /**
     *
     * @throws PersistenceException :
     */
    static void displayComputerDetails() throws PersistenceException {
        DetailsComputerView.INSTANCE.displayUI();
    }

    /**
     *
     * @throws PersistenceException :
     */
    static void displayComputerUpdate() throws PersistenceException {
        UpdateComputerView.INSTANCE.displayUI();
    }

}
