package com.excilys.computerdatabase.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.excilys.computerdatabase.validation.EntityValidation;

/**
 * @author Guillon Julien
 *
 *         20 févr. 2017
 */

@Entity
@Table(name="computer")
public class Computer implements Serializable {
    @Id
    private long id;
 
    @Column(name="name")
    private String name;
    
    @Column(name="introduced")
    private LocalDate introduced;
    
    @Column(name="discontinued")
    private LocalDate discontinued;
    
    @ManyToOne
    private Company company;

    public Computer() {
        
    }
    
    public String getName() {
        return name;
    }

    public LocalDate getIntroduced() {
        return introduced;
    }

    public LocalDate getDiscontinued() {
        return discontinued;
    }

    public Company getManufacturer() {
        return company;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIntroduced(LocalDate introduced) {
        this.introduced = introduced;
    }

    public void setDiscontinued(LocalDate discontinued) {
        this.discontinued = discontinued;
    }

    public void setManufacturer(Company manufacturer) {
        this.company = manufacturer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     *
     * @return a Builder
     */
    public static Builder builder() {
        return new Builder();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
        result = prime * result + ((company == null) ? 0 : company.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Computer other = (Computer) obj;
        if (discontinued == null) {
            if (other.discontinued != null) {
                return false;
            }
        } else if (!discontinued.equals(other.discontinued)) {
            return false;
        }
        if (id != other.id) {
            return false;
        }
        if (introduced == null) {
            if (other.introduced != null) {
                return false;
            }
        } else if (!introduced.equals(other.introduced)) {
            return false;
        }
        if (company == null) {
            if (other.company != null) {
                return false;
            }
        } else if (!company.equals(other.company)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
                + ", manufacturer=" + company + "]";
    }

    public static class Builder {
        private Computer computer;

        /**
         *
         */
        public Builder() {
            computer = new Computer();
        }
        /**
         *
         * @param id :
         * @return a Builder
         */
        public Builder withId(long id) {
            this.computer.id = id;
            return this;
        }

        /**
         *
         * @param name :
         * @return a Builder
         */
        public Builder withName(String name) {
            if (EntityValidation.nameIsValid(Optional.ofNullable(name))) {
                this.computer.name = name;
            }
            return this;
        }

        /**
         *
         * @param introduced :
         * @return a Builder
         */
        public Builder withIntroduced(LocalDate introduced) {
            this.computer.introduced = introduced;
            return this;
        }

        /**
         *
         * @param discontinued :
         * @return a Builder
         */
        public Builder withDiscontinued(LocalDate discontinued) {
            this.computer.discontinued = discontinued;
            return this;
        }

        /**
         *
         * @param manufacturer :
         * @return a Builder
         */
        public Builder withManufacturer(Company manufacturer) {
            this.computer.company = manufacturer;
            return this;
        }

        /**
         *
         * @return a Computer
         */
        public Computer build() {
            return computer;
        }
    }
}
