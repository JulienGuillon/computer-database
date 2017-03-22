package com.excilys.computerdatabase.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.excilys.computerdatabase.entity.Company;

/**
 * @author Guillon Julien
 *
 *         22 févr. 2017
 */
public class MapperCompany {

    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_ID = "id";

    /**
     * Allows to get a company from a ResultSet.
     *
     * @param optionalResultSet :
     * @return an Optional Company
     */
    public static Optional<Company> resultSetToCompany(Optional<ResultSet> optionalResultSet) {
        Company.Builder companyBuilder = null;
        if (optionalResultSet.isPresent()) {
            ResultSet resultSet = optionalResultSet.get();
            try {
                companyBuilder = new Company.Builder().withId(resultSet.getLong(COLUMN_ID))
                        .withName(resultSet.getString(COLUMN_NAME));
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return Optional.of(companyBuilder.build());
    }
}
