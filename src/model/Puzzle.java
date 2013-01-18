package model;

import java.sql.Time;
import java.util.ArrayList;

public class Puzzle {
	private String start_code;
	
	private String flavor_text;
	
	private int total_fan_value;
	private int current_fan_worth;
	
	private int elapsed_minutes;
	private int par_minutes;
	private Time start_time;
	private Time end_time;
	private Time time_of_last_hint;
	
	private String status;

	private ArrayList<Entry> guesses_made;
	
	private ArrayList<Hint> hints;
	private ArrayList<Answer> answers;
	private ArrayList<Resource> resources_unlocked;
	private ArrayList<Team> teams_killed;
}
