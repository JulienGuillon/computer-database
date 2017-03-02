package com.excilys.computerdatabase.views;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import com.excilys.computerdatabase.controllers.UpdateComputerController;
import com.excilys.computerdatabase.entities.Computer;
import com.excilys.computerdatabase.validations.DateValidation;
import com.excilys.computerdatabase.validations.EntityValidation;
import com.excilys.computerdatabase.validations.SelectionValidation;

/**
 * @author Guillon Julien
 *
 * 21 févr. 2017
 *
 * View that allows to select a computer by an id and modify it to make an update
 */
public enum UpdateComputerView {
    INSTANCE;

    private UpdateComputerController updateComputerControler;

    private Scanner sc = ScannerInstance.INSTANCE.getScanner();

    /**
     *
     */
    UpdateComputerView() {
        updateComputerControler = UpdateComputerController.INSTANCE;
        updateComputerControler.setUpdateComputerView(Optional.ofNullable(this));
    }

    /**
     *
     */
    public void displayHeader() {
        System.out.println("\t\t UPDATE COMPUTER \t\t");
    }

    /**
     *
     */
    public void displayUI() {
        String id;
        displayHeader();
        do {
            System.out.print("Select computer's id to modify it: ");
            id = sc.next();
        } while (!SelectionValidation.idIsValid(Optional.ofNullable(id)));
        updateComputerControler.findComputerById(Integer.parseInt(id));
    }

    /**
     * @param optionalComputer :
     */
    public void displayDetailsToUpdate(Optional<Computer> optionalComputer) {
        if (optionalComputer.isPresent()) {
            Computer computer = optionalComputer.get();
            System.out.println("ID (" + computer.getId() + ")");
            String name = "";
            LocalDate introduced = null;
            LocalDate discontinued = null;
            String s = null;
            sc.nextLine();
            System.out.print("NAME (" + computer.getName() + "): ");
            name = sc.nextLine();
            if (StringUtils.isBlank(name) || EntityValidation.nameIsValid(Optional.ofNullable(name))) {
                name = computer.getName();
            }
            System.out.print("INTRODUCED (" + computer.getIntroduced() + "): ");
            s = sc.nextLine();
            if (DateValidation.formatIsValid(Optional.ofNullable(s))) {
                introduced = LocalDate.parse(s);
            } else {
                introduced = computer.getIntroduced();
            }

            s = "";
            System.out.print("DISCONTINUED (" + computer.getDiscontinued() + "): ");
            s = sc.nextLine();
            if (DateValidation.formatIsValid(Optional.ofNullable(s)) && DateValidation
                    .dateIsValid(Optional.ofNullable(introduced), Optional.ofNullable(LocalDate.parse(s)))) {
                discontinued = LocalDate.parse(s);
            } else {
                discontinued = computer.getDiscontinued();
            }
            computer.setIntroduced(introduced);
            computer.setDiscontinued(discontinued);
            computer.setName(name);
            computer.setManufacturer(null);
            updateComputerControler.update(Optional.ofNullable(computer));
        }
    }

    /**
     * @param optionalComputer :
     */
    public void displayInfoComputer(Optional<Computer> optionalComputer) {
        if (optionalComputer.isPresent()) {
            Computer computer = optionalComputer.get();
            System.out.println("Computer with ID: " + computer.getId() + " was update");
            IView.displayMainMenu();
        }
    }

}
