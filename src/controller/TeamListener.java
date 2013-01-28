package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLayeredPane;

import view.MainPanel;
import view.TeamPanel;

import model.Team;

public class TeamListener implements ActionListener {
	private final Team team;
	
	public TeamListener(Team t) {
		super();
		team = t;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JLayeredPane pane = new JLayeredPane();
		TeamPanel panel = new TeamPanel();
		for (Component c : Controller.mainWindow.getContentPane().getComponents()) {
			if (c instanceof JLayeredPane) {
				pane = (JLayeredPane)c;
				break;
			}
		}
		for (Component c : pane.getComponents()) {
			if (c instanceof TeamPanel) {
				panel = (TeamPanel)c;
				break;
			}
			else if (c instanceof MainPanel) {
				for (Component cx : ((MainPanel)c).getComponents())
					cx.setEnabled(false);
			}
		}
		
		
		panel.showTeamData(team);
		pane.moveToFront(panel);
	}

}
