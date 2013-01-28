package view;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

import model.Session;

public class MainWindow extends JFrame {
	final static String WINDOW_TITLE = "Famine Game";
	
	
	protected static Session session;
	
	private static void addPanelsToPane(Container container) {
		// Set up content container
		container.setLayout(null);
			
		// TOP PANEL
		container.add(new TopPanel());
		
		// SIDE PANEL
		container.add(new SidePanel());	
		
		// MAIN PANEL AREA
		JLayeredPane lp = new JLayeredPane();
		MainPanel mp = new MainPanel();
		mp.setOpaque(true);
		TeamPanel tp = new TeamPanel();
		tp.setOpaque(true);
		lp.setBounds(224,100,800,468);
		lp.add(mp, 0);
		lp.add(tp, 1);
		container.add(lp);
		
		// BOTTOM PANEL
		container.add(new BottomPanel());
	}
	
	private void createAndShowGUI() {
		//Create and set up the window.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Set up the content pane.
		addPanelsToPane(this.getContentPane());

		//Display the window.
		this.pack();
		this.setSize(1024,768);
		this.setResizable(false);
		this.setVisible(true);

	}

	public MainWindow(Session session) {
		super(WINDOW_TITLE);
		this.session = session;
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
