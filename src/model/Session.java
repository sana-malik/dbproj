package model;

import java.sql.Time;
import java.util.ArrayList;

import utilities.XMLReader;

public class Session {
	private String teamId;
	
	private Time start_time;
	private int elapsed_time;
	
	private Team active_team;
	
	private Location current_location;
	
	private ArrayList<Team> team_list;
	private ArrayList<Puzzle> active_puzzle_list;
	private ArrayList<Resource> unlocked_resource_list;
	private ArrayList<Location> locations;
	
	public Session(String teamId) {
		team_list = new ArrayList<Team>();
		active_team = new Team(teamId);
		team_list.add(active_team);
		
		locations = new ArrayList<Location>();
		locations.add( XMLReader.readLocation("data/location1.xml") );
		
		current_location = locations.get(0);
	}
	
	public String getTeamId() {
		return active_team.getId();
	}

	public ArrayList<Puzzle> getActivePuzzles() {
		return active_puzzle_list;
	}
	
	public String checkEntry(String entry) {
		String response = "";
		
		// Check active puzzles for an answer
		
		
		// Check this location's puzzles for a start code
		ArrayList<Puzzle> local_puzzles = current_location.checkStartCodes( entry );
		
		for( int index = 0; index < local_puzzles.size(); index++ )
			response += local_puzzles.get(index).getFlavorText() + "\n";
		
		if ( response.equals("") )
			response = "Unrecognized entry.";
		
		return response;
	}
}
