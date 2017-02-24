package com.excilys.computerdatabase.view;

import java.time.LocalDate;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import com.excilys.computerdatabase.controller.UpdateComputerController;
import com.excilys.computerdatabase.entities.Computer;
import com.excilys.computerdatabase.validation.DateValidation;
import com.excilys.computerdatabase.validation.EntityValidation;
import com.excilys.computerdatabase.validation.SelectionValidation;

/**
 * @author Guillon Julien
 *
 * 21 févr. 2017
 * 
 * View that allows to select a computer by an id 
 * and modify it to make an update
 */
public enum UpdateComputerView {
	INSTANCE;
		
	private UpdateComputerController updateComputerControler;
	
	private Scanner sc = ScannerInstance.INSTANCE.getScanner();
	
	private UpdateComputerView()
	{
		updateComputerControler = UpdateComputerController.INSTANCE;
		updateComputerControler.setUpdateComputerView(this);
	}
	
	
	public void displayHeader()
	{
		System.out.println("\t\t UPDATE COMPUTER \t\t");
	}
	
	public void displayUI()
	{
		String id;
		displayHeader();
		do {
			System.out.print("Select computer's id to modify it: ");
			id = sc.next();
		}
		while(!SelectionValidation.idIsValid(id));
		updateComputerControler.findComputerById(Integer.parseInt(id));
	}

	/**
	 * @param computer
	 */
	public void displayDetailsToUpdate(Computer computer) {
		System.out.println("ID (" + computer.getId() + ")");
		String name = "";
		LocalDate introduced = null;
		LocalDate discontinued = null;
		String s = null;
		sc.nextLine();
		System.out.print("NAME (" + computer.getName() + "): ");
		name = sc.nextLine();
		if(StringUtils.isBlank(name) || EntityValidation.nameIsValid(name))
		{
			name = computer.getName();
		}
		System.out.print("INTRODUCED (" + computer.getIntroduced() + "): ");
		s = sc.nextLine();
		if (DateValidation.formatIsValid(s))
		{
			introduced = LocalDate.parse(s);
		}
		else
		{
			introduced = computer.getIntroduced();
		}
		
		s = "";
		System.out.print("DISCONTINUED (" + computer.getDiscontinued() + "): ");
		s = sc.nextLine();
		if (DateValidation.formatIsValid(s) && DateValidation.dateIsValid(introduced, discontinued))
		{
			discontinued = LocalDate.parse(s);
		}
		else
		{
			discontinued = computer.getDiscontinued();
		}
		computer.setIntroduced(introduced);
		computer.setDiscontinued(discontinued);
		computer.setName(name);
		updateComputerControler.update(computer, computer.getId());
	}

	/**
	 * @param computer
	 */
	public void displayInfoComputer(Computer computer)
	{
		System.out.println("Computer with ID: " + computer.getId() + " was update");
		IView.displayMainMenu();
	}
	
	
	

}
