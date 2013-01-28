package controller;

import view.MainWindow;
import model.Session;

public class Controller {
	protected static Session session;
	protected static MainWindow mainWindow;
	
	public Controller(Session session, MainWindow mainWindow) {
		this.session = session;
		this.mainWindow = mainWindow;
	}
	
}
