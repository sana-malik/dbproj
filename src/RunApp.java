import java.util.ArrayList;

import controller.Controller;

import model.Location;
import model.Session;
import utils.XMLReader;
import view.MainWindow;


public class RunApp {
	public static void main(String[] args) {
		Session session = new Session(0); // create model
		MainWindow mainWindow = new MainWindow(session); // create view & bind model
		Controller controller = new Controller(session, mainWindow); // create controller & bind view and model
	}
}
