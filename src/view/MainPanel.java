package view;

import java.awt.Button;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import utils.Utilities;

import model.Session;

public class MainPanel extends JPanel{
	public static JPanel create() {
		JPanel panel = new JPanel();
		JLabel text = new JLabel("<html><p>This is the main panel, which will house clues and a main dashboard which will be history-ish.</p></html>");
		panel.add(text);
		
		// following code is test/proof of concept!
		TextField testIn = new TextField(40); // random length, input
		JTextArea testOut = new JTextArea(20,60); // output
		
		Button buttonGo = new Button("Go!");
		
		
		panel.add(testIn);
		panel.add(buttonGo);
		panel.add(testOut);
		
		
		ButtonAction ba = new ButtonAction(testIn, testOut);
		buttonGo.addActionListener(ba);
		// end proof of concept
		
		panel.setBorder(BorderFactory.createLineBorder (Color.blue, 2));
		return panel;
	}
}

// proof of concept contd
class ButtonAction implements ActionListener {
	private TextField in;
	private JTextArea out;

	public ButtonAction(TextField in, JTextArea out) {
		this.in = in;
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