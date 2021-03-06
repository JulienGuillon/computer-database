package com.excilys.computerdatabase.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.excilys.computerdatabase.entity.Company;
import com.excilys.computerdatabase.entity.Computer;

/**
 * @author Guillon Julien
 *
 *         22 févr. 2017
 */
public class MapperComputer {

    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_INTRODUCED = "introduced";
    private static final String COLUMN_DISCONTINUED = "discontinued";
    private static final String COLUMN_COMPANY_ID = "company_id";
    private static final String COLUMN_COMPANY_NAME = "company_name";

    /**
     * Allows to get a computer from a ResultSet.
     *
     * @param optionalResultSet :
     * @return an OptionalComputer
     */
    public static Optional<Computer> resultSetToComputer(Optional<ResultSet> optionalResultSet) {
        Computer.Builder computerBuilder = null;
        if (optionalResultSet.isPresent()) {
            ResultSet resultSet = optionalResultSet.get();
            try {
                computerBuilder = new Computer.Builder().withId(resultSet.getLong(COLUMN_ID))
                        .withName(resultSet.getString(COLUMN_NAME));

                Timestamp date = resultSet.getTimestamp(COLUMN_INTRODUCED);
                if (date != null) {
                    LocalDate dateIntroduced = date.toLocalDateTime().toLocalDate();
                    computerBuilder.withIntroduced(dateIntroduced);
                }
                date = resultSet.getTimestamp(COLUMN_DISCONTINUED);
                if (date != null) {
                    LocalDate dateDiscontinued = date.toLocalDateTime().toLocalDate();
                    computerBuilder.withDiscontinued(dateDiscontinued);
                }
                Long companyId = resultSet.getLong(COLUMN_COMPANY_ID);
                Company.Builder companyBuilder = null;
                if (companyId != null) {
                    companyBuilder = new Company.Builder().withId(companyId);
                }
                String companyName = resultSet.getString(COLUMN_COMPANY_NAME);
                if (StringUtils.isNotBlank(companyName)) {
                    companyBuilder = companyBuilder.withName(companyName);
                }
                computerBuilder.withManufacturer(companyBuilder.build());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Optional.ofNullable(computerBuilder.build());
    }
}
