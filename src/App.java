
import com.excilys.computerdatabase.validation.SelectionValidation;
import com.excilys.computerdatabase.view.IView;

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
	public static void main(String[] args)
	{
		SelectionValidation.userChoiceIsValid("n");		
		IView.displayMainMenu();
	}
}
