import java.util.ArrayList;

import model.Location;
import model.Session;
import utils.XMLReader;
import view.MainWindow;


public class RunApp {
	public static void main(String[] args) {
		
		ArrayList<Location> locations = new ArrayList<Location>();
		locations.add( XMLReader.readLocation("data\\location1.xml") );
		
		Session session = new Session("01");
		MainWindow mainWindow = new MainWindow(session);
	}
}
