package com.excilys.computerdatabase.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerdatabase.dao.PaginationCompany;
import com.excilys.computerdatabase.entities.Company;
import com.excilys.computerdatabase.exceptions.PersistenceException;
import com.excilys.computerdatabase.services.ServiceCompany;
import com.excilys.computerdatabase.views.ListCompanyView;

/**
 * @author Guillon Julien
 *
 *         21 févr. 2017
 *
 *         Controller for the ListCompanyView It is a singleton. Allows to catch
 *         event on view ListCompanyView and make validation.
 *
 */
public enum ListCompanyController {
    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(ListCompanyController.class);

    private ListCompanyView listCompanyView;

    private int offset;

    private ServiceCompany serviceCompany = ServiceCompany.INSTANCE;

    private PaginationCompany paginationCompany = new PaginationCompany();

    /**
     *
     */
    ListCompanyController() {
    }

    /**
     * Find all companies with pagination by using crud method and call to.
     * update view
     *
     * @param optionalChoice :
     * @throws PersistenceException
     */
    public void findCompanies(Optional<String> optionalChoice) {
        if (optionalChoice.isPresent()) {
            String choice = optionalChoice.get();
            boolean quit = false;
            switch (choice) {
            case "n":
                offset = offset + 1;
                break;
            case "p":
                offset = offset - 1 >= 0 ? offset - 1 : -1;
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
                List<Company> companies = paginationCompany.getPage(offset);
                listCompanyView.displayCompanies(companies);
            }
        }
    }

    /**
     * @param optionalListCompanyView
     * the listCompanyView to set
     */
    public void setListCompanyView(Optional<ListCompanyView> optionalListCompanyView) {
        if (optionalListCompanyView.isPresent()) {
            this.listCompanyView = optionalListCompanyView.get();
        }
    }
}
