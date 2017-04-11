package com.excilys.computerdatabase.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Guillon Julien
 *
 * 1 mars 2017
 */

@XmlRootElement(name="computer")
public class ComputerDTO implements Serializable {
    private long id;

    private String name;
    
    private String introduced;
    
    private String discontinued;
    
    private String manufacturerName;
    
    private long manufacturerId;

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the introduced
     */
    public String getIntroduced() {
        return introduced;
    }

    /**
     * @return the discontinued
     */
    public String getDiscontinued() {
        return discontinued;
    }

    /**
     * @return the manufacturerName
     */
    public String getManufacturerName() {
        return manufacturerName;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param introduced the introduced to set
     */
    public void setIntroduced(String introduced) {
        this.introduced = introduced;
    }

    /**
     * @param discontinued the discontinued to set
     */
    public void setDiscontinued(String discontinued) {
        this.discontinued = discontinued;
    }

    /**
     * @param manufacturerName the manufacturerName to set
     */
    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    /**
     * @return the manufacturerId
     */
    public long getManufacturerId() {
        return manufacturerId;
    }

    /**
     * @param manufacturerId the manufacturerId to set
     */
    public void setManufacturerId(long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }
    
    

    @Override
	public String toString() {
		return "ComputerDTO [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued="
				+ discontinued + ", manufacturerName=" + manufacturerName + ", manufacturerId=" + manufacturerId + "]";
	}

	public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private ComputerDTO computerDto;

        /**
         *
         */
        public Builder() {
            computerDto = new ComputerDTO();
        }

        public Builder withId(int id) {
            this.computerDto.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.computerDto.name = name;
            return this;
        }

        public Builder withIntroduced(String introduced) {
            this.computerDto.introduced = introduced;
            return this;
        }

        public Builder withDiscontinued(String discontinued) {
            this.computerDto.discontinued = discontinued;
            return this;
        }

        public Builder withManufacturerId(int manufacturerId) {
            this.computerDto.manufacturerId = manufacturerId;
            return this;
        }

        public Builder withManufacturerName(String manufacturerName) {
            this.computerDto.manufacturerName =  manufacturerName;
            return this;
        }

        public ComputerDTO build() {
            return computerDto;
        }
    }
}
