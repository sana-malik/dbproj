package model;

import java.sql.Time;
import java.util.ArrayList;

public class Session {
	private String teamId;
	
	private Time start_time;
	private int elapsed_time;
	
	private Team active_team;
	
	private ArrayList<Team> team_list;
	private ArrayList<Puzzle> active_puzzle_list;
	private ArrayList<Resource> unlocked_resource_list;
	
	public Session(String teamId) {
		team_list = new ArrayList<Team>();
		active_team = new Team(teamId);
		team_list.add(active_team);
	}
	
	public String getTeamId() {
		return active_team.getId();
	}
}
