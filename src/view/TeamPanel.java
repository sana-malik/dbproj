package view;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import model.Session;
import model.Team;

public class TeamPanel extends JPanel {
	public TeamPanel() {
		JLabel text = new JLabel("<html><p>The team panel will have the team's informations when clicked.</p></html>");
		this.add(text);
		this.setBorder(BorderFactory.createLineBorder (Color.orange, 2));
		this.setBounds(0,0,800,468);
	}
	
	public void showTeamData(Team t) {
		JLabel text= new JLabel("<html><p>meow meow meow " + t.getId() + "</p></html>");
		this.add(text);
		this.revalidate();
	}
}