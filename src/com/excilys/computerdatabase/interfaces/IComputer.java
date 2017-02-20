package com.excilys.computerdatabase.interfaces;

import java.util.Date;

import com.excilys.computerdatabase.entities.Company;

/**
 * @author Guillon Julien
 *
 * 20 févr. 2017
 */
public interface IComputer {

	public String getName();
	
	public Date getIntroduced();
	
	public Date getDiscontinued();
	
	public Company getManufacturer();
	
	public void setName(String pName);
	
	public void setIntroduced(Date pIntroduced);
	
	public void setDiscontinued(Date pDiscontinued);
	
	public void setManufacturer(Company pManufacturer);
	
	public default String show() {
		return "Computer [name=" + getName() +
				", introduced=" + getIntroduced() +
				", discontinued=" + getDiscontinued()
				+ ", manufacturer=" + getManufacturer() + "]";
	}
}
