package view;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

import model.Session;

public class MainWindow {
	final static String WINDOW_TITLE = "Famine Game";
	
	
	protected static Session session;
	
	public static void addPanelsToPane(Container container) {
		// Set up content container
		container.setLayout(new GridBagLayout());
		
		// constraints for all panels
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		
		// TOP PANEL
		c.weightx = 1;				// 100% width
		c.weighty = 0.2;			// 20% height
		c.gridwidth = 2;			// span two columns
		c.gridx = 0;				// first row
		c.gridy = 0;				// first column
		container.add(TopPanel.create(), c);
		
		// SIDE PANEL
		c.weightx = 0.15;			// 15% width
		c.weighty = 0.8;			// 80% height
		c.gridwidth = 1;			// span one column
		c.gridx = 0;				// first column
		c.gridy = 1;				// second row
		container.add(SidePanel.create(), c);	
		
		// MAIN PANEL
		c.weightx = 0.85;			// 85% width
		c.weighty = 0.8;			// 80% height
		c.gridwidth = 1;			// span one column
		c.gridx = 1;				// second column
		c.gridy = 1;				// second row
		container.add(MainPanel.create(), c);
		
		
		// BOTTOM PANEL
		c.weightx = 1;				// 100% width
		c.weighty = 0.2;			// 20% height
		c.gridwidth = 2;			// span two columns
		c.gridx = 0;				// first column
		c.gridy = 2;				// third row
		c.anchor = GridBagConstraints.PAGE_END; 	// last component
		container.add(BottomPanel.create(), c);
	}
	
	private static void createAndShowGUI() {
		//Create and set up the window.
		JFrame frame = new JFrame(WINDOW_TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Set up the content pane.
		addPanelsToPane(frame.getContentPane());

		//Display the window.
		frame.pack();
		frame.setSize(1024,768);
		frame.setResizable(false);
		frame.setVisible(true);

	}

	public MainWindow(Session session) {
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
