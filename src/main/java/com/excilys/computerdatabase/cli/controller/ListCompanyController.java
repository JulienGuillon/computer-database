package com.excilys.computerdatabase.cli.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.computerdatabase.cli.view.ListCompanyView;
import com.excilys.computerdatabase.entity.Company;
import com.excilys.computerdatabase.exception.PersistenceException;
import com.excilys.computerdatabase.pagination.Page;
import com.excilys.computerdatabase.service.ServiceCompany;
import com.excilys.computerdatabase.service.impl.ServiceCompanyImpl;
import com.excilys.computerdatabase.springConfig.CdbConfiguration;

/**
 * @author Guillon Julien
 *
 * 21 févr. 2017
 *
 * Controller for the ListCompanyView It is a singleton. Allows to catch
 * event on view ListCompanyView and make validation.
 *
 */
public enum ListCompanyController {
    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(ListCompanyController.class);

    private ListCompanyView listCompanyView;

    private int offset;

    private ApplicationContext context = new AnnotationConfigApplicationContext(CdbConfiguration.class);
    
    private ServiceCompany serviceCompany = context.getBean(ServiceCompanyImpl.class);

    private Page<Company> page = new Page();

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
            	page.setPage(offset);
                page = serviceCompany.getPage(page);
                listCompanyView.displayCompanies(page.getElements());
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
