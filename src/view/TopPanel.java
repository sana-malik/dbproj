package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class TopPanel {
	public static JPanel create() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder (Color.green, 2));
		return panel;
	}
}
