package model;

public class Session {
	private String teamId;
	private int time_since_last_answer;
	
	public Session(String teamId) {
		this.teamId = teamId;
	}
	
	public String getTeamId() {
		return teamId;
	}
}
