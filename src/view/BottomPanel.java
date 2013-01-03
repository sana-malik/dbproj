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

public class BottomPanel {
	public static JPanel create() {
		JPanel panel = new JPanel();
		BufferedImage myPicture;
		try {
			for (int i = 1; i < 10; i++) {
				myPicture = ImageIO.read(new File("images/team0"+i+".gif"));
				JLabel picLabel = new JLabel(new ImageIcon(myPicture));
				panel.add(picLabel);
			}
			for (int i = 10; i <=24; i++) {
				myPicture = ImageIO.read(new File("images/team"+i+".gif"));
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
