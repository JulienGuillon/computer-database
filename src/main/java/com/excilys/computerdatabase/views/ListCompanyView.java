package com.excilys.computerdatabase.views;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.excilys.computerdatabase.controllers.ListCompanyController;
import com.excilys.computerdatabase.entities.Company;
import com.excilys.computerdatabase.exceptions.PersistenceException;
import com.excilys.computerdatabase.validations.SelectionValidation;

/**
 * @author Guillon Julien
 *
 * 21 févr. 2017
 *
 * View that display list of companies
 *
 */
public enum ListCompanyView {
    INSTANCE;

    private ListCompanyController listCompanyControler;

    private Scanner sc = ScannerInstance.INSTANCE.getScanner();

    /**
     *
     */
    ListCompanyView() {
        listCompanyControler = ListCompanyController.INSTANCE;
        listCompanyControler.setListCompanyView(Optional.ofNullable(this));
    }

    /**
     *
     */
    public void displayHeader() {
        System.out.format(ConstanteView.FORMAT_COMPANY, "ID", "NAME");
    }

    /**
     * Display footer that able to select next page or previous page.
     */
    public void displayFooter() {
        String choice;
        do {
            System.out.println("\t\t previous(p) \t\t quit(q) \t\t next(n)");
            choice = sc.next();
            while (!SelectionValidation.userChoiceIsValid(Optional.ofNullable(choice))) {
                choice = sc.next();
                System.out.println("\t\t previous(p) \t\t quit(q) \t\t next(n)");
            }
            listCompanyControler.findCompanies(Optional.ofNullable(choice));
        } while (!choice.equals("q"));
        IView.displayMainMenu();
    }

    /**
     * @throws PersistenceException
     * @throws Exception
     */
    public void displayUI() {
        System.out.print("Choose size of page: ");
        listCompanyControler.findCompanies(Optional.ofNullable(""));
        displayFooter();
    }

    /**
     * Display view that show list of companies.
     *
     * @param companies :
     */
    public void displayCompanies(List<Company> companies) {
        displayHeader();
        for (Company company : companies) {
            System.out.format(ConstanteView.FORMAT_COMPANY, company.getId(), company.getName());
        }
    }

}
