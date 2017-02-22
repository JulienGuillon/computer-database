package com.excilys.computerdatabase.interfaces;

import java.util.Date;

/**
 * @author Guillon Julien
 *
 * 20 févr. 2017
 */
public interface IComputer {

	public String getName();
	
	public Date getIntroduced();
	
	public Date getDiscontinued();
	
	public ICompany getManufacturer();
	
	public void setName(String pName);
	
	public void setIntroduced(Date pIntroduced);
	
	public void setDiscontinued(Date pDiscontinued);
	
	public void setManufacturer(ICompany company);
	
	public int getId();
	
	public void setId(int pId);
	
	public default String show() {
		return "Computer [name=" + getName() +
				", introduced=" + getIntroduced() +
				", discontinued=" + getDiscontinued()
				+ ", manufacturer=" + getManufacturer() + "]";
	}
}
