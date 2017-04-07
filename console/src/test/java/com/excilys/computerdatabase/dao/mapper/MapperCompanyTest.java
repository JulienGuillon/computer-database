package com.excilys.computerdatabase.dao.mapper;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.excilys.computerdatabase.entity.Company;
import com.excilys.computerdatabase.persistence.mapper.MapperCompany;

/**
 * @author Guillon Julien
 *
 * 27 févr. 2017
 */
public class MapperCompanyTest {

    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_ID = "id";
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
    public void resultSetToCompany() throws SQLException {
        Mockito.when(resultSet.getLong(COLUMN_ID)).thenReturn((long) 2);
        Mockito.when(resultSet.getString(COLUMN_NAME)).thenReturn("Name");
        Optional<Company> optionalCompany = MapperCompany.resultSetToCompany(
                Optional.ofNullable(resultSet));
        if (optionalCompany.isPresent()) {
            Company company =  optionalCompany.get();
            assertEquals(company.getId(), 2);
            assertEquals(company.getName(), "Name");
        }
    }

    /**
     *
     * @throws SQLException :
     */
    @Test
    public void resultSetToCompanyWithNameNull() throws SQLException {
        Mockito.when(resultSet.getLong(COLUMN_ID)).thenReturn((long) 2);
        Mockito.when(resultSet.getString(COLUMN_NAME)).thenReturn(null);
        Optional<Company> optionalCompany = MapperCompany.resultSetToCompany(
                Optional.ofNullable(resultSet));
        if (optionalCompany.isPresent()) {
            Company company =  optionalCompany.get();
            assertEquals(company.getId(), 2);
            assertEquals(company.getName(), null);
        }
    }
}
