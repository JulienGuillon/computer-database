package com.excilys.computerdatabase.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.excilys.computerdatabase.entities.Company;

/**
 * @author Guillon Julien
 *
 * 22 févr. 2017
 */
public class MapperCompany {

	private static final String COLUMN_NAME = "name";
	private static final String COLUMN_ID = "id";

	/**
	 * Allows to get a company from a ResultSet 
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public static Optional<Company> resultSetToCompany(ResultSet resultSet)
	{
		Company.Builder companyBuilder = null;
		try {
			companyBuilder = new Company.Builder()
					.withId(resultSet.getLong(COLUMN_ID))
					.withName(resultSet.getString(COLUMN_NAME));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Optional.of(companyBuilder.build());
	}
}
