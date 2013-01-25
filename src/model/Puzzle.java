package model;

import java.sql.Time;
import java.util.ArrayList;

public class Puzzle {

	private String name;
	private String start_code;
	
	private String flavor_text;
	
	private int max_fan_worth;
	private int current_fan_worth;
	
	private int elapsed_minutes;
	private int par_time;
	private Time start_time;
	private Time end_time;
	private Time time_of_last_hint;
	
	private String status;

	private ArrayList<Entry> guesses_made;
	
	private ArrayList<Hint> hints;
	private ArrayList<Answer> answers;
	private ArrayList<String> resources_unlocked;
	private ArrayList<String> teams_killed;
	
	public Puzzle(String name, String start_code, String flavor_text,
			int max_fan_worth, int par_time, ArrayList<Hint> hints,
			ArrayList<Answer> answers, ArrayList<String> resources_unlocked,
			ArrayList<String> teams_killed) {

		this.name = name;
		this.start_code = start_code;
		
		this.flavor_text = flavor_text;
		
		this.max_fan_worth = max_fan_worth;
		this.current_fan_worth = max_fan_worth;
		
		this.elapsed_minutes = 0;
		this.par_time = par_time;
		this.start_time = new Time(0);
		this.end_time = new Time(0);
		this.time_of_last_hint = new Time(0);
		
		this.status = "unstarted";
		
		this.guesses_made = new ArrayList<Entry>();
		
		this.hints = hints;
		this.answers = answers;
		this.resources_unlocked = resources_unlocked;
		this.teams_killed = teams_killed;
	}
}
