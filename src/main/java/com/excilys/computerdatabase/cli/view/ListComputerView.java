package com.excilys.computerdatabase.cli.view;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.excilys.computerdatabase.cli.controller.ListComputerController;
import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.validation.SelectionValidation;

/**
 * @author Guillon Julien
 *
 * 21 févr. 2017
 *
 * View that display list of computers
 *
 */
public enum ListComputerView {
    INSTANCE;

    private ListComputerController listComputerControler;

    private Scanner sc = ScannerInstance.INSTANCE.getScanner();

    /**
     *
     */
    ListComputerView() {
        listComputerControler = ListComputerController.INSTANCE;
        listComputerControler.setListComputerView(Optional.ofNullable(this));
    }

    /**
     *
     */
    public void displayHeader() {
        System.out.format(ConstanteView.FORMAT_COMPUTER, "ID", "NOM", "INTRODUCED", "DISCONTINUED", "COMPANY");
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
            listComputerControler.findComputers(Optional.ofNullable(choice));
        } while (!choice.equals("q"));
        IView.displayMainMenu();
    }

    /**
     *
     */
    public void displayUI() {
        displayHeader();
        listComputerControler.findComputers(Optional.ofNullable(""));
        displayFooter();
    }

    /**
     * Display view that show list of computers.
     *
     * @param computers :
     */
    public void displayComputers(List<Computer> computers) {
        for (Computer computer : computers) {
            System.out.format(ConstanteView.FORMAT_COMPUTER, computer.getId(), computer.getName(),
                    (computer.getIntroduced() == null) ? "" : computer.getIntroduced(),
                    (computer.getDiscontinued() == null) ? "" : computer.getDiscontinued(),
                    (computer.getManufacturer() == null) ? "" : computer.getManufacturer().getName());
            }
    }

}
