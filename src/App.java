
import java.sql.ResultSet;

import java.util.Date;

import com.excilys.computerdatabase.dao.CrudCompany;
import com.excilys.computerdatabase.dao.CrudComputer;
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
	
	public static void connectDb() throws Exception
	{
		IComputer computer;
		CrudComputer crudComputer = new CrudComputer();
		ResultSet resultSet = crudComputer.findAll();
		resultSet.next();
		System.out.println(resultSet.getString("name"));
		System.out.println(resultSet.getString("cname"));
		System.out.println(resultSet.getObject("introduced"));

		computer = crudComputer.find(5);
	
		System.out.print(computer.getName()+" ");
		System.out.print(computer.getManufacturer().getName()+ " ");
		System.out.print(computer.getIntroduced()+ " ");

			
		computer = new Computer.ComputerBuilder("TEHHHT").build();
		ICompany company = new Company.CompanyBuilder("tets").build();
		company.setId(7);
		computer.setManufacturer(company);
		
		crudComputer.update(computer, 575);
		
		/**
		CrudCompany crudCompany = new CrudCompany();
		ICompany company  = crudCompany.find(6);
		System.out.println(company);
		**/
		
		CrudCompany crudCompany = new CrudCompany();
		System.out.println("\n"+crudCompany.findUsingPagination(1));
	}

}
