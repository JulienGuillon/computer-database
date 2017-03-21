package com.excilys.computerdatabase.dao.mapper;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.persistence.mapper.MapperComputer;

/**
 * @author Guillon Julien
 *
 * 27 févr. 2017
 */
public class MapperComputerTest {

    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_INTRODUCED = "introduced";
    private static final String COLUMN_DISCONTINUED = "discontinued";
    private static final String COLUMN_COMPANY_ID = "company_id";
    private static final String COLUMN_COMPANY_NAME = "company_name";
    private ResultSet resultSet;

    /**
     *
     */
    @Before
    public void initBeforeEachTest() {
        resultSet = Mockito.mock(ResultSet.class);
    }

    /**
     *
     * @throws SQLException :
     */
    @Test
    public void resultSetToComputer() throws SQLException {

        Mockito.when(resultSet.getLong(COLUMN_ID)).thenReturn((long) 2);
        Mockito.when(resultSet.getString(COLUMN_NAME)).thenReturn("Name");

        Mockito.when(resultSet.getTimestamp(COLUMN_INTRODUCED)).thenReturn(Timestamp.valueOf("2010-05-02 00:00:00"));
        Mockito.when(resultSet.getTimestamp(COLUMN_DISCONTINUED)).thenReturn(Timestamp.valueOf("2013-05-02 00:00:00"));

        Mockito.when(resultSet.getLong(COLUMN_COMPANY_ID)).thenReturn((long) 15);
        Mockito.when(resultSet.getString(COLUMN_COMPANY_NAME)).thenReturn("Name Company");
        Optional<Computer> optionalComputer = MapperComputer.resultSetToComputer(Optional.ofNullable(resultSet));
        if (optionalComputer.isPresent()) {
            Computer computer = optionalComputer.get();
            assertEquals(computer.getId(), 2);
            assertEquals(computer.getName(), "Name");
            assertEquals(computer.getIntroduced(), LocalDate.parse("2010-05-02"));
            assertEquals(computer.getDiscontinued(), LocalDate.parse("2013-05-02"));

            assertEquals(computer.getManufacturer().getId(), 15);
            assertEquals(computer.getManufacturer().getName(), "Name Company");
        }
    }

    /**
     *
     * @throws SQLException :
     */
    @Test
    public void resultSetToComputerWithoutDate() throws SQLException {

        Mockito.when(resultSet.getLong(COLUMN_ID)).thenReturn((long) 2);
        Mockito.when(resultSet.getString(COLUMN_NAME)).thenReturn("Name");

        Mockito.when(resultSet.getTimestamp(COLUMN_INTRODUCED)).thenReturn(null);
        Mockito.when(resultSet.getTimestamp(COLUMN_DISCONTINUED)).thenReturn(null);

        Mockito.when(resultSet.getLong(COLUMN_COMPANY_ID)).thenReturn((long) 15);
        Mockito.when(resultSet.getString(COLUMN_COMPANY_NAME)).thenReturn("Name Company");
        Optional<Computer> optionalComputer = MapperComputer.resultSetToComputer(Optional.ofNullable(resultSet));
        if (optionalComputer.isPresent()) {
            Computer computer = optionalComputer.get();
            assertEquals(computer.getId(), 2);
            assertEquals(computer.getName(), "Name");
            assertEquals(computer.getIntroduced(), null);
            assertEquals(computer.getDiscontinued(), null);

            assertEquals(computer.getManufacturer().getId(), 15);
            assertEquals(computer.getManufacturer().getName(), "Name Company");
        }
    }
}
