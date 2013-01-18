package model;

public class Session {
	private int time_since_last_answer;
	private Team active_team;
	
	public Session(String teamId) {
		active_team = new Team(teamId);
	}
	
	public String getTeamId() {
		return active_team.getId();
	}
}
