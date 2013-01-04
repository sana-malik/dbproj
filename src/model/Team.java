package model;

public class Team {
	private String teamID;
	private String name;
	private int size;
	private String bio;
	
	private boolean killed;
	private int district;
	
	private String death_video_path;

	public Team(String teamID, String name, int size, String bio,
			boolean killed, int district, String death_video_path) {
		this.teamID = teamID;
		this.name = name;
		this.size = size;
		this.bio = bio;
		this.killed = killed;
		this.district = district;
		this.death_video_path = death_video_path;
	}
	
	public Team(String teamID) {
		this(teamID, "No name", 0, "No bio", false, -1, "");
	}

	public String getTeamID() {
		return teamID;
	}
	
	
}
