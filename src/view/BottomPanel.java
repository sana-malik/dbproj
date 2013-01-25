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

import model.Team;

public class BottomPanel {
	public static JPanel create() {
		JPanel panel = new JPanel();
		BufferedImage myPicture;
		try {
			for (Team t : MainWindow.session.getTeamList()) {
				myPicture = ImageIO.read(new File("images/team" + t.getId() +".gif"));
				JLabel picLabel = new JLabel(new ImageIcon(myPicture));
				panel.add(picLabel);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return panel;
	}
}
