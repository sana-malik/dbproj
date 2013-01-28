package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class TopPanel extends JPanel {
	public TopPanel() {
		this.setBorder(BorderFactory.createLineBorder (Color.green, 2));
		this.setBounds(0, 0, 1024, 100);
	}
}
