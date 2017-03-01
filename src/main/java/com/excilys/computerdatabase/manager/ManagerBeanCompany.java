package com.excilys.computerdatabase.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.computerdatabase.dao.CrudCompany;
import com.excilys.computerdatabase.dao.impl.CrudCompanyImpl;
import com.excilys.computerdatabase.entities.Company;

/**
 * @author Guillon Julien
 *
 * 1 mars 2017
 */
public class ManagerBeanCompany {

    private CrudCompany crudCompany = CrudCompanyImpl.INSTANCE;

    public List<Company> getCompanies() {
        List<Company> companies = new ArrayList<>();
        Optional<List<Optional<Company>>> optionalListOfCompany = crudCompany.findAll();
        if (optionalListOfCompany.isPresent()) {
            List<Optional<Company>> optionalCompanies = optionalListOfCompany.get();
            for (Optional<Company> optionalCompany : optionalCompanies) {
                if(optionalCompany.isPresent()) {
                    companies.add(optionalCompany.get());
                }
            }
        }
        return companies;
    }
}
