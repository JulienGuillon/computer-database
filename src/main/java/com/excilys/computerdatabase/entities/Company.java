package com.excilys.computerdatabase.entities;

import java.util.Optional;

import com.excilys.computerdatabase.validation.EntityValidation;

/**
 * @author Guillon Julien
 *
 *         20 févr. 2017
 */
public class Company {

    private long id;
    private String name;

    /**
     *
     */
    public Company() {
    }

    public String getName() {
        return name;
    }

    /**
     *
     * @param name :
     * @throws Exception :
     */
    public void setName(String name) throws Exception {
        this.name = name;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     *
     * @return a builder
     */
    public static Builder builder() {
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
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Company other = (Company) obj;
        if (id != other.id) {
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

    public static class Builder {

        private Company company;

        /**
         *
         */
        public Builder() {
            company = new Company();
        }

        /**
         *
         * @param name :
         * @return a Builder
         */
        public Builder withName(String name) {
            if (EntityValidation.nameIsValid(Optional.ofNullable(name))) {
                this.company.name = name;
            }
            return this;
        }

        /**
         *
         * @param id :
         * @return a Builder
         */
        public Builder withId(long id) {
            this.company.id = id;
            return this;
        }

        /**
         *
         * @return a Company
         */
        public Company build() {
            return company;
        }
    }
}
