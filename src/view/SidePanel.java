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

public class SidePanel extends JPanel {
	public SidePanel() {
		JLabel text = new JLabel("<html><p width=\"40%\">The side panel will have the team's informations.</p></html>");
		this.add(text);
		
		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(new File(MainWindow.session.getActiveTeam().getIconAliveFile()));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			this.add(picLabel);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setBorder(BorderFactory.createLineBorder (Color.red, 2));
		this.setBounds(0, 100, 224, 468);
	}
}