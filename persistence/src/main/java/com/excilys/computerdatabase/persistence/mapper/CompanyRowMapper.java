package com.excilys.computerdatabase.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.computerdatabase.entity.Company;

/**
 * @author Guillon Julien
 *
 * 3 avr. 2017
 */
public class CompanyRowMapper implements RowMapper {

    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_ID = "id";
    
    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
     */
    @Override
    public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Company.Builder companyBuilder = null;
        try {
            companyBuilder = new Company.Builder().withId(resultSet.getLong(COLUMN_ID))
                    .withName(resultSet.getString(COLUMN_NAME));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companyBuilder.build();
    }

}
