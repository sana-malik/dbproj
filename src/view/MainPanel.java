package view;

import java.awt.Button;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainPanel {
	public static JPanel create() {
		JPanel panel = new JPanel();
		JLabel text = new JLabel("<html><p>This is the main panel, which will house clues and a main dashboard which will be history-ish.</p></html>");
		panel.add(text);
		
		// following code is test/proof of concept!
		TextField testIn = new TextField(40); // random length, input
		TextField testOut = new TextField(40); // output
		Button buttonGo = new Button("Go!");
		
		panel.add(testIn);
		panel.add(testOut);
		panel.add(buttonGo);
		
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
	  private TextField out;

	  public ButtonAction(TextField in, TextField out) {
	    this.in = in;
	    this.out = out;
	  }

	  public void actionPerformed(ActionEvent ae) {

	    String s = in.getText();
	    out.setText(s);

	  }
}
// end POC