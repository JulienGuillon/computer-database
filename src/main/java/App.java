import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.computerdatabase.dao.PaginationComputer;
import com.excilys.computerdatabase.springConfig.AppConfig;

/**
 * @author Guillon Julien
 *
 * 20 févr. 2017
 */
public class App {

    /**
     * @param args :
     */
    public static void main(String[] args) {
        //IView.displayMainMenu();
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
     
        PaginationComputer paginationComputer = context.getBean(PaginationComputer.class);
        System.out.println(paginationComputer.getPageNumber(1));
    }
}
