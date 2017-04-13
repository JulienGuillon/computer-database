import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerdatabase.cli.view.IView;
import com.excilys.computerdatabase.dto.ComputerDTO;

/**
 * @author Guillon Julien
 *
 * 20 févr. 2017
 */
public class CliLauncher {

    private static final Logger LOGGER = LoggerFactory.getLogger(CliLauncher.class);

    /**
     * @param args :
     */
    public static void main(String[] args) {
        //IView.displayMainMenu();
    	String url = "http://localhost:8080/webservice/computers/64";

    	URL obj;
		try {
			obj = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
			int status = conn.getResponseCode();

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
				ObjectMapper mapper = new ObjectMapper();
				ComputerDTO computer = mapper.readValue(inputLine, ComputerDTO.class);
				System.out.println(computer.toString());
			}
			in.close();
		} catch (IOException e) {

		}
    }
    
}
