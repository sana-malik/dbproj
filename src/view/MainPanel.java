package view;

import java.awt.Button;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import utils.Utilities;

import model.Session;

public class MainPanel extends JPanel {
	public MainPanel() {
		JLabel text = new JLabel("<html><p>This is the main panel, which will house clues and a main dashboard which will be history-ish.</p></html>");
		this.add(text);
		
		// following code is test/proof of concept!
		JTextField testIn = new JTextField(40); // random length, input
		JTextArea testOut = new JTextArea(20,60); // output
		
		JButton buttonGo = new JButton("Go!");
		
		
		this.add(testIn);
		this.add(buttonGo);
		this.add(testOut);
		
		
		ButtonAction ba = new ButtonAction(testIn, testOut);
		buttonGo.addActionListener(ba);
		// end proof of concept
		
		this.setBorder(BorderFactory.createLineBorder (Color.blue, 2));
		this.setBounds(0, 0, 800, 468);
	}
}

// proof of concept contd
class ButtonAction implements ActionListener {
	private JTextField in;
	private JTextArea out;

	public ButtonAction(JTextField testIn, JTextArea out) {
		this.in = testIn;
		this.out = out;
	}

	public void actionPerformed(ActionEvent ae) {
		String entry = Utilities.normalizeText( in.getText() );
		
		String response = MainWindow.session.checkEntry( entry );
		
		System.out.println("*"+out.getText()+"*");
		out.insert( response + "\n", 0 );
	}
}
// end POC