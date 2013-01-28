package view;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.Team;
import controller.TeamListener;

public class BottomPanel extends JPanel {
	public BottomPanel() {
		BufferedImage myPicture;
		try {
			for (final Team t : MainWindow.session.getTeamList()) {
				myPicture = ImageIO.read(new File(t.getIconAliveFile()));
				JButton buttonTeam = new JButton(new ImageIcon(myPicture));
				buttonTeam.setBorder(BorderFactory.createEmptyBorder());
				buttonTeam.setContentAreaFilled(false);
				this.add(buttonTeam);
				
				buttonTeam.addActionListener(new TeamListener(t));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.setBorder(BorderFactory.createLineBorder (Color.yellow, 2));
		this.setBounds(0,568,1024,178);
	}
}
