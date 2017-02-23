
import com.excilys.computerdatabase.view.IView;
import com.excilys.computerdatabase.view.validation.SelectionValidation;

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
		SelectionValidation.nameIsValid("N_");		
		IView.displayMainMenu();
	}
}
