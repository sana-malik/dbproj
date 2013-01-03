package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainPanel {
	public static JPanel create() {
		JPanel panel = new JPanel();
		JLabel text = new JLabel("<html><p>This is the main panel, which will house clues and a main dashboard which will be history-ish.</p></html>");
		panel.add(text);
		panel.setBorder(BorderFactory.createLineBorder (Color.blue, 2));
		return panel;
	}
}
