import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.excilys.computerdatabase.dao.ConstanteDatabase;
import com.excilys.computerdatabase.dao.CrudCompany;
import com.excilys.computerdatabase.dao.CrudComputer;
import com.excilys.computerdatabase.dao.ManagerDatabase;
import com.excilys.computerdatabase.entities.Company;
import com.excilys.computerdatabase.entities.Computer;
import com.excilys.computerdatabase.interfaces.ICompany;
import com.excilys.computerdatabase.interfaces.IComputer;

/**
 * @author Guillon Julien
 *
 * 20 févr. 2017
 */
public class App {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		IComputer computer = null;
		computer = new Computer.ComputerBuilder("test").setIntroduced(new Date()).build();
		System.out.println(computer.show());
		connectDb();
	}
	
	public static void connectDb() throws ClassNotFoundException, SQLException
	{
		CrudComputer crudComputer = new CrudComputer();
		ResultSet resultSet = crudComputer.findAll();
		System.out.println(resultSet.next());
		System.out.println(resultSet.getString("name"));
		System.out.println(resultSet.getInt("id"));

		CrudCompany crudCompany = new CrudCompany();
		ICompany company  = crudCompany.find(6);
		System.out.println(company);
		
	}

}
