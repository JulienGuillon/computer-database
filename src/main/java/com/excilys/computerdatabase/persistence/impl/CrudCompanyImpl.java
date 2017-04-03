package com.excilys.computerdatabase.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.entity.Company;
import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.exception.PersistenceException;
import com.excilys.computerdatabase.pagination.Page;
import com.excilys.computerdatabase.persistence.CrudCompany;
import com.excilys.computerdatabase.persistence.Datasource;
import com.excilys.computerdatabase.persistence.mapper.CompanyRowMapper;
import com.excilys.computerdatabase.persistence.mapper.ComputerRowMapper;
import com.excilys.computerdatabase.persistence.mapper.MapperCompany;
import com.excilys.computerdatabase.util.LoadProperties;

/**
 * @author Guillon Julien
 *
 *         20 févr. 2017
 *
 *         Allows to make all the crud operation on entity company
 */

@Repository
public class CrudCompanyImpl implements CrudCompany {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CrudCompanyImpl.class);

    private LoadProperties loadProperties = LoadProperties.INSTANCE;

    private Properties properties;

    private static final String SELECT_COMPANIES = "SELECT_COMPANIES";
    private static final String SELECT_COMPANY_BY_ID = "SELECT_COMPANY_BY_ID";
    private static final String PAGINATION_COMPANIES = "PAGINATION_COMPANIES";
    private static final String SELECT_COMPANIES_NUMBER = "SELECT_COMPANIES_NUMBER";

    private ResultSet resultSet;

    @Autowired
    private Datasource dataSource;
    
    private JdbcTemplate jdbcTemplateObject;

    @PostConstruct
    public void setDataSource() {

       this.jdbcTemplateObject = new JdbcTemplate(dataSource);

    }

    public CrudCompanyImpl() {
        loadProperties.initLoadProperties("queries.properties");
        properties = loadProperties.getProperties();
    }

    /**
     * @param id :
     * @return an Optional Company
     */
    public Optional<Company> find(Connection connection, long id) {
        Company company = null;
        try {
            company = (Company) jdbcTemplateObject.queryForObject(properties.getProperty(SELECT_COMPANY_BY_ID), new Object[] {id}, new CompanyRowMapper());
        } catch (DataAccessException dataAccessException) {
            throw new PersistenceException(dataAccessException);
        }
        return Optional.ofNullable(company);
    }

    /**
     * @return an Optional ResultSet
     */
    public List<Company> findAll(Connection connection) {
        List<Company> companies = new ArrayList<>();
        try {
            companies = jdbcTemplateObject.query(properties.getProperty(SELECT_COMPANIES), new CompanyRowMapper());
        } catch (DataAccessException dataAccessException) {
            throw new PersistenceException(dataAccessException);
        }
        return companies;
    }

    /* (non-Javadoc)
     * @see com.excilys.computerdatabase.dao.Crud#getNumber()
     */
    @Override
    public int getNumber(Connection connection, String filter) {
        int number;
        try {
            number = jdbcTemplateObject.queryForObject(properties.getProperty(SELECT_COMPANIES_NUMBER), new Object[] {filter + "%"}, Integer.class);
        } catch (DataAccessException dataAccessException) {
            throw new PersistenceException(dataAccessException);
        }
        return number;
    }

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.persistence.Crud#getPage(com.excilys.computerdatabase.pagination.Page)
	 */
	@Override
	public Page<Company> getPage(Connection connection, Page<Company> page) {
	    List<Company> companies = new ArrayList<>();
	    try {
    	    companies = (List<Company>) jdbcTemplateObject.query(properties.getProperty(PAGINATION_COMPANIES), new CompanyRowMapper(),
                    page.getElementsByPage(), page.getPage()*page.getElementsByPage());
            page.setElements(companies);
            page.setTotalElements(getNumber(connection, page.getFilter()));
	    } catch (DataAccessException dataAccessException) {
            throw new PersistenceException(dataAccessException);
        }
	    return page;
	}

}
