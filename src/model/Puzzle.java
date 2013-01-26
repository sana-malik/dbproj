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
	private int current_hint;
	
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
		this.current_hint = 0;
		
		this.hints = hints;
		this.answers = answers;
		this.resources_unlocked = resources_unlocked;
		this.teams_killed = teams_killed;
	}

	@Override
	public String toString() {
		return "Puzzle [name=" + name + ", start_code=" + start_code
				+ ", flavor_text=" + flavor_text + ", max_fan_worth="
				+ max_fan_worth + ", current_fan_worth=" + current_fan_worth
				+ ", elapsed_minutes=" + elapsed_minutes + ", par_time="
				+ par_time + ", start_time=" + start_time + ", end_time="
				+ end_time + ", time_of_last_hint=" + time_of_last_hint
				+ ", status=" + status + ", guesses_made=" + guesses_made
				+ ", hints=" + hints + ", answers=" + answers
				+ ", resources_unlocked=" + resources_unlocked
				+ ", teams_killed=" + teams_killed + "]";
	}

	public boolean isStartCode(String code) {
		return this.start_code.equals(code);
	}

	public String activatePuzzle() {
		// TODO Auto-generated method stub
		return flavor_text;
	}

	public String checkAnswer(String entry) {
		String response = "";
		int index = 0;
		
		while ( index < answers.size() && !answers.get(index).checkAnswer( entry ) ) {
			index++;
		}
		
		if ( index != answers.size() )
			response = answers.get(index).getResponse();
		
		return response;
	}
	
	
}
