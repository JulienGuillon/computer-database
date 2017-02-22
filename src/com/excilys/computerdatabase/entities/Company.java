package com.excilys.computerdatabase.entities;

import com.excilys.computerdatabase.validation.ICheck;

/**
 * @author Guillon Julien
 *
 * 20 févr. 2017
 */
public class Company {
	
	private long id;
	private String name;
	
	private Company()
	{
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws Exception {
		ICheck.isNull(name);
		this.name = name;
	}
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	public static Builder builder()
	{
		return new Builder();
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}



	public static class Builder {
		
		private Company company;
		
		public Builder()
		{
			company = new Company();
		}
		
		public Builder withName(String name)
		{
			this.company.name = name;
			return this;
		}
		
		public Builder withId(long id)
		{
			this.company.id = id;
			return this;
		}
		
		public Company build()
		{
			return company;
		}
	}
}
