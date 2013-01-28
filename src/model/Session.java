package model;

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
	
	public Session(int team_index) {
		team_list = XMLReader.readTeamList("data/Teams.xml");
		
		active_team = team_list.get(team_index);

		start_time = Calendar.getInstance().getTime();
		elapsed_time = 0;
		
		active_puzzle_list = new ArrayList<Puzzle> ();
		unlocked_resource_list = new ArrayList<Resource> ();
		
		locations = new ArrayList<Location>();
		locations.add( XMLReader.readLocation("location1.xml") );
		
		current_location = locations.get(0);
	}
	
	public Team getActiveTeam() {
		return active_team;
	}

	public ArrayList<Puzzle> getActivePuzzles() {
		return active_puzzle_list;
	}
	
	public ArrayList<Team> getTeamList() {
		return team_list;
	}
	
	public String checkEntry(String entry) {
		String response = "";
		int answer_index = -1;
		
		// Check active puzzles for an answer
		int index = 0;
		while( index < active_puzzle_list.size() && answer_index == -1) {
			answer_index = active_puzzle_list.get(index).getAnswerIndex( entry );
			index++;
		}
		
		index--;  // compensate for the extra ++ after a match above

		if ( answer_index != -1 ) {
			Puzzle matching_puzzle = active_puzzle_list.get(index); 
			response = matching_puzzle.getAnswerResponse(answer_index);
			
			if ( matching_puzzle.getAnswerType( answer_index ).equals("final")) {
				int earned_fans = matching_puzzle.closePuzzle();
				active_puzzle_list.remove( index );
				active_team.addFans(earned_fans);
				response += "\nYou have earned " + earned_fans + " fans.\n";
			}
		}
				
		if ( response.equals("") ) {
			// Check this location's puzzles for a start code
			ArrayList<Puzzle> local_puzzles = current_location.checkStartCodes( entry );
			local_puzzles.removeAll( active_puzzle_list );  // this is a list of newly activated puzzles
			active_puzzle_list.addAll( local_puzzles );
			
			for( index = 0; index < local_puzzles.size(); index++ )
				response += local_puzzles.get(index).activatePuzzle();
		}

		if ( response.equals("") )
			response = "Unrecognized entry.";

		return response;
	}

	public void tick() {
		// TODO Auto-generated method stub
		elapsed_time++;
		
		for ( int index=0; index < active_puzzle_list.size(); index++)
			active_puzzle_list.get(index).tick();
	}
	
	public int getElapsedTime() {
		return elapsed_time;
	}

	public String getHint() {
		// TODO Auto-generated method stub
		
		// TODO This only works if we have one active puzzle 
		// (with more puzzles, we need to specify which one we want a hint for
		return active_puzzle_list.get(0).getHint();
	}
}
