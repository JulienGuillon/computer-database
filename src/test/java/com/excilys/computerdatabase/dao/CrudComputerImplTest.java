package com.excilys.computerdatabase.dao;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;

import com.excilys.computerdatabase.dao.impl.CrudComputerImpl;
import com.excilys.computerdatabase.entities.Computer;
import com.excilys.computerdatabase.utils.LoadProperties;


/**
 * @author Guillon Julien
 *
 * 2 mars 2017
 */
public class CrudComputerImplTest {
  /**  
    @Before
    public void importDataSet() throws Exception {
        IDataSet dataSet = readDataSet();
        cleanlyInsert(dataSet);
    }

    private IDataSet readDataSet() throws Exception {
        InputStream input = LoadProperties.class.getClassLoader().getResourceAsStream("computer_dataset.xml");

        return new FlatXmlDataSetBuilder().build(input);
    }

    private void cleanlyInsert(IDataSet dataSet) throws Exception {
        IDatabaseTester databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/computer-database-db","admincdb", "qwerty1234");
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
    }

    @Test
    public void findsAndReadsExistingPersonByFirstName() throws Exception {
        CrudComputerImpl crud = CrudComputerImpl.INSTANCE;
        Computer computer = crud.find(1).get();

        assertEquals(computer.getId(), 1);
        //assertThat(charlie.getLastName(), is("Brown"));
        //assertThat(charlie.getAge(), is(42));
    }
**/
}
