import java.util.ArrayList;

import model.Location;
import model.Session;
import utilities.XMLReader;
import view.MainWindow;


public class RunApp {
	public static void main(String[] args) {
		Session session = new Session("01");
		MainWindow mainWindow = new MainWindow(session);
	}
}
