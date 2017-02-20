package com.excilys.computerdatabase.interfaces;

/**
 * @author Guillon Julien
 *
 * 20 févr. 2017
 */
public interface ICompany {

	public String getName();
	
	public void setName(String pName) throws Exception;
	
	public default String show() {
		return "Company [name=" + getName() + "]";
	}
}
