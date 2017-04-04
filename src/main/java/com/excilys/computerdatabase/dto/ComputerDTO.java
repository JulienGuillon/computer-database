package com.excilys.computerdatabase.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Guillon Julien
 *
 * 1 mars 2017
 */
public class ComputerDTO {
    @Min(0)
    private long id;
    
    @NotNull
    @Size(min = 2, max = 20)
    @Pattern(regexp="[\\w-_.\\s]*", message="Not valid !")
    private String name;
    
    @Pattern(regexp="(\\d{4}-\\d{2}-\\d{2}|\\d{2}-\\d{2}-\\d{4})")
    private String introduced;
    
    @Pattern(regexp="\\d{4}-\\d{2}-\\d{2}")
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
