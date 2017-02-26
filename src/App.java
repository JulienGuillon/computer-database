
import com.excilys.computerdatabase.exception.PersistenceException;
import com.excilys.computerdatabase.view.IView;

/**
 * @author Guillon Julien
 *
 * 20 févr. 2017
 */
public class App {

	/**
	 * @param args
	 * @throws PersistenceException 
	 * @throws Exception 
	 */
	public static void main(String[] args) throws PersistenceException
	{
		IView.displayMainMenu();
	}
}
