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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

import utilities.Utils;

public class MainPanel extends JPanel{
	static Timer timer;
	public static JTextArea outText;
	
	public MainPanel() {
		JLabel text = new JLabel("<html><p>This is the main panel, which will house clues and a main dashboard which will be history-ish.</p></html>");
		this.add(text);
		
		// following code is test/proof of concept!
		JTextField testIn = new JTextField(40); // random length, input
		this.add(testIn);
		
		outText = new JTextArea(20,60);
		outText.setEditable(false);
		
		JScrollPane testOut = new JScrollPane(outText);
		
		Button buttonGo = new Button("Submit");
		Button hintButton = new Button("Gimme a hint!");
		
		this.add(testOut);
		this.add(buttonGo);
		this.add(hintButton);
		
		HintAction ha = new HintAction();
		hintButton.addActionListener(ha);		
		
		ButtonAction ba = new ButtonAction(testIn, outText);
		buttonGo.addActionListener(ba);
		testIn.addActionListener(ba);
		
		TimerAction ta = new TimerAction();
		// this timer spawns an event every minute 
		timer = new Timer(60000, ta);
		
		// Quick timer for testing, spawning an event every 10 seconds
		//timer = new Timer(10000, ta);
		
		timer.start(); 
		// end proof of concept
		
		this.setBorder(BorderFactory.createLineBorder (Color.blue, 2));
		this.setBounds(0, 0, 800, 468);
	}
}

// proof of concept contd
class HintAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to unlock a hint?", "Yes", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
        	String response = MainWindow.session.getHint();
        	MainPanel.outText.insert( response + "\n", 0);
        	MainPanel.outText.setCaretPosition(0);
        } 
	}
	
}

class TimerAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		MainWindow.session.tick();
		//MainPanel.outText.insert( MainWindow.session.getElapsedTime() + " minutes have passed since you started.\n", 0);
	}
	
}

class ButtonAction implements ActionListener {
	private JTextField in;
	private JTextArea out;

	public ButtonAction(JTextField testIn, JTextArea out) {
		this.in = testIn;
		this.out = out;
	}

	public void actionPerformed(ActionEvent ae) {
		String entry = Utils.normalizeText( in.getText() );
		
		String response = MainWindow.session.checkEntry( entry );
		
		out.insert( response + "\n", 0);
		out.setCaretPosition(0);
		in.setText("");
	}
}
// end POC