package com.excilys.computerdatabase.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.excilys.computerdatabase.entities.Computer;

/**
 * @author Guillon Julien
 *
 * 22 févr. 2017
 */
public class MapperComputer {

	private static final String COLUMN_NAME = "name";
	private static final String COLUMN_ID = "id";
	private static final String COLUMN_INTRODUCED = "introduced";
	private static final String COLUMN_DISCONTINUED = "discontinued";

	/**
 	 * Allows to get a computer from a ResultSet
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public static Computer setToComputer(ResultSet resultSet) throws SQLException, Exception
	{
		Computer.Builder computerBuilder = Computer.builder()
				.withId(resultSet.getLong(COLUMN_ID))
				.withName(resultSet.getString(COLUMN_NAME));
		Date dateIntroduced = (Date) resultSet.getObject(COLUMN_INTRODUCED);
		if (dateIntroduced != null)
		{
				computerBuilder.withIntroduced(dateIntroduced);
		}
		Date dateDiscontinued= (Date) resultSet.getObject(COLUMN_DISCONTINUED);
		if (dateDiscontinued != null)
		{
				computerBuilder.withDiscontinued(dateDiscontinued);
		}
		return computerBuilder.build();
	}
}
