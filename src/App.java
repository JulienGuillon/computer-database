import java.util.Date;

import com.excilys.computerdatabase.entities.Computer;
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
	}

}
