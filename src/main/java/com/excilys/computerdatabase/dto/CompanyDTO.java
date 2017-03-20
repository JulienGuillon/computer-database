package com.excilys.computerdatabase.dto;

/**
 * @author Guillon Julien
 *
 * 3 mars 2017
 */
public class CompanyDTO {

    private int id;
    private String name;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public static Builder builder() {
        return new Builder();
    }

    private static class Builder {
        private CompanyDTO companyDto;

        public Builder() {
            companyDto = new CompanyDTO();
        }

        public Builder withId(int id) {
            this.companyDto.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.companyDto.name = name;
            return this;
        }

        public CompanyDTO build() {
            return companyDto;
        }
    }
}
