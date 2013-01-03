package view;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Session;

public class SidePanel {
	public static JPanel create() {
		JPanel panel = new JPanel();
		JLabel text = new JLabel("<html><p width=\"40%\">The side panel will have the team's informations.</p></html>");
		panel.add(text);
		
		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(new File("images/team"+MainWindow.session.getTeamId()+".gif"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			panel.add(picLabel);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		panel.setBorder(BorderFactory.createLineBorder (Color.red, 2));
		return panel;
	}
}