package com.excilys.computerdatabase.view;

import java.util.Scanner;

/**
 * @author Guillon Julien
 *
 * 2017-02-21
 * 
 */
public enum ScannerInstance {
	INSTANCE;
	
	private Scanner scanner;
	
	private ScannerInstance()
	{
		scanner = new Scanner(System.in);
	}
	
	public Scanner getScanner()
	{
		return scanner;
	}
}
