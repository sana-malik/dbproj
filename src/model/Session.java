package model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import utilities.XMLReader;

public class Session {
	private Date start_time;
	private int elapsed_time;
	
	private Team active_team;
	
	private Location current_location;
	
	private ArrayList<Team> team_list;
	private ArrayList<Puzzle> active_puzzle_list;
	private ArrayList<Resource> unlocked_resource_list;
	private ArrayList<Location> locations;
	
	public Session(String teamId) {
		start_time = Calendar.getInstance().getTime();
		elapsed_time = 0;
		
		team_list = new ArrayList<Team>();
		active_team = new Team(teamId);
		team_list.add(active_team);
		
		active_puzzle_list = new ArrayList<Puzzle> ();
		unlocked_resource_list = new ArrayList<Resource> ();
		
		locations = new ArrayList<Location>();
		locations.add( XMLReader.readLocation("location1.xml") );
		
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
		for (int index = 0; index < active_puzzle_list.size(); index++ ){
			response = active_puzzle_list.get(index).checkAnswer( entry );
		}
				
		if ( response.equals("") ) {
			// Check this location's puzzles for a start code
			ArrayList<Puzzle> local_puzzles = current_location.checkStartCodes( entry );
			local_puzzles.removeAll( active_puzzle_list );  // this is a list of newly activated puzzles
			active_puzzle_list.addAll( local_puzzles );
			
			for( int index = 0; index < local_puzzles.size(); index++ )
				response += local_puzzles.get(index).activatePuzzle();
		}

		if ( response.equals("") )
			response = "Unrecognized entry.";

		return response;
	}
}
