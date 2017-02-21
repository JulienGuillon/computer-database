package com.excilys.computerdatabase.entities;

import java.util.Date;

import com.excilys.computerdatabase.interfaces.ICompany;
import com.excilys.computerdatabase.interfaces.IComputer;
import com.excilys.computerdatabase.validation.IComputerValidation;

/**
 * @author Guillon Julien
 *
 * 20 févr. 2017
 */
public class Computer implements IComputer{
	private int id;
	private String name;
	private Date introduced;
	private Date discontinued;
	private ICompany manufacturer;
	
	private Computer(ComputerBuilder pComputerBuilder)
	{
		name = pComputerBuilder.name;
		introduced = pComputerBuilder.introduced;
		discontinued = pComputerBuilder.discontinued;
		manufacturer = pComputerBuilder.manufacturer;
	}
	

	@Override
	public String getName() {
		return name;
	}


	@Override
	public Date getIntroduced() {
		return introduced;
	}


	@Override
	public Date getDiscontinued() {
		return discontinued;
	}


	@Override
	public ICompany getManufacturer() {
		return manufacturer;
	}


	@Override
	public void setName(String pName) {
		name = pName;
	}


	@Override
	public void setIntroduced(Date pIntroduced) {
		introduced = pIntroduced;
	}


	@Override
	public void setDiscontinued(Date pDiscontinued) {
		discontinued = pDiscontinued;
	}


	@Override
	public void setManufacturer(ICompany pManufacturer) {
		manufacturer = pManufacturer;
	}
	
	public static class ComputerBuilder
	{
		private String name;
		private Date introduced;
		private Date discontinued;
		private Company manufacturer;
		
		public ComputerBuilder(String pName)
		{
			name = pName;
		}
		
		public ComputerBuilder setIntroduced(Date pIntroduced)
		{
			introduced = pIntroduced;
			return this;
		}
		
		public ComputerBuilder setDiscontinued(Date pDiscontinued)
		{
			discontinued = pDiscontinued;
			return this;
		}
		
		public ComputerBuilder setManufacturer(Company pManufacturer)
		{
			manufacturer = pManufacturer;
			return this;
		}
		
		public IComputer build() throws Exception
		{
			IComputer computer = new Computer(this);
			IComputerValidation.check(computer);
			return computer; 
		}
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.interfaces.IComputer#getId()
	 */
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}


	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.interfaces.IComputer#setId(int)
	 */
	@Override
	public void setId(int pId) {
		// TODO Auto-generated method stub
		id = pId;		
	}
	
	
	
}

