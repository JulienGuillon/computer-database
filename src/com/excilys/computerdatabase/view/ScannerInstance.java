package com.excilys.computerdatabase.view;

import java.util.Scanner;

/**
 * @author Guillon Julien
 *
 * 2017-02-21
 * 
 */
public class ScannerInstance {
	private static final Scanner SCANNER = new Scanner(System.in);
	
	public static Scanner getInstance()
	{
		return SCANNER;
	}
	
	public static void close()
	{
		SCANNER.close();
	}
}
