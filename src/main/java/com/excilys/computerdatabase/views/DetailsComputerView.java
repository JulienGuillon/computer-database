package com.excilys.computerdatabase.views;

import java.util.Optional;
import java.util.Scanner;

import com.excilys.computerdatabase.controllers.DetailsComputerController;
import com.excilys.computerdatabase.entities.Computer;

/**
 * @author Guillon Julien
 *
 *         21 févr. 2017
 *
 *         View that display informations about one computer
 *
 */
public enum DetailsComputerView {
    INSTANCE;

    private DetailsComputerController detailsComputerControler;

    private long idComputer;

    private Scanner sc = ScannerInstance.INSTANCE.getScanner();

    /**
     *
     */
    DetailsComputerView() {
        detailsComputerControler = DetailsComputerController.INSTANCE;
        detailsComputerControler.setDetailsComputerView(Optional.ofNullable(this));
    }

    /**
     *
     */
    public void displayHeader() {
        System.out.println("\t\t SHOW COMPUTER DETAIL \t\t");
    }

    /**
     *
     */
    public void displayFooter() {
        System.out.println("\t\t delete(d) \t\t update(u) \t\t");
        detailsComputerControler.makeOperation(Optional.ofNullable(sc.next()), idComputer);
    }

    /**
     * Display view to select computer's id and use controller to get associated computer.
     */
    public void displayUI() {
        int choice;
        displayHeader();
        do {
            System.out.print("Select computer's id to show its details: ");
            choice = sc.nextInt();
        } while (choice <= 0);
        detailsComputerControler.findComputerById(choice);
        displayFooter();
        IView.displayMainMenu();
    }

    /**
     * Display details of a computer.
     *
     * @param optionalComputer :
     */
    public void displayDetails(Optional<Computer> optionalComputer) {
        if (optionalComputer.isPresent()) {
            Computer computer = optionalComputer.get();
            System.out.format(ConstanteView.FORMAT_COMPUTER, "ID", "NOM", "INTRODUCED", "DISCONTINUED", "COMPANY");
            System.out.format(ConstanteView.FORMAT_COMPUTER, computer.getId(), computer.getName(),
                    (computer.getIntroduced() == null) ? "" : computer.getIntroduced(),
                    (computer.getDiscontinued() == null) ? "" : computer.getDiscontinued(),
                    (computer.getManufacturer() == null) ? "" : computer.getManufacturer().getName());
            idComputer = computer.getId();
        }
    }

    /**
     * Display a message to confirm deletion of a computer.
     * @param id :
     */
    public void displayDeletion(long id) {
        System.out.println("Computer with ID: " + id + " was deleted");
    }
}
