package model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import utilities.Const.Hint_Status;
import utilities.Const.Puzzle_Status;
import view.MainPanel;

public class Puzzle {

	private String name;
	private String start_code;
	
	private String flavor_text;
	
	private int max_fan_worth;
	private int current_fan_worth;
	
	private int elapsed_minutes;
	private int par_time;
	private Date start_time;
	private Date end_time;
	private Date time_of_last_hint;
	
	private Puzzle_Status status;

	private ArrayList<Entry> guesses_made;
	private int current_hint_index;
	
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
		
		this.status = Puzzle_Status.LOCKED;
		
		this.guesses_made = new ArrayList<Entry>();
		this.current_hint_index = 0;
		
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
		status = Puzzle_Status.ACTIVE;
		start_time = Calendar.getInstance().getTime();
		return flavor_text;
	}

	public int getAnswerIndex(String entry) {
		int index = 0;
		
		while ( index < answers.size() && !answers.get(index).checkAnswer( entry ) ) {
			index++;
		}
		
		if ( index == answers.size() ) 
			index = -1;
		
		return index;
	}
	
	public String getAnswerType(int index){
		return answers.get(index).getType();
	}
	
	public String getAnswerResponse(int index){
		return answers.get(index).getResponse();
	}
	
	public int closePuzzle() {
		// TODO Auto-generated method stub
		status = Puzzle_Status.SOLVED;
		end_time = Calendar.getInstance().getTime();
		
		return current_fan_worth;
	}

	public void tick() {
		// TODO Auto-generated method stub
		elapsed_minutes++;
		
		for( int index = 0; index < hints.size(); index++ ) {
			Hint hint = hints.get(index);
			
			if ( hint.getStatus() == Hint_Status.LOCKED && elapsed_minutes >= hint.getMinutes_till_available() ) {
				hint.setStatus( Hint_Status.AVAILABLE );
				MainPanel.outText.insert("A new hint is now available.\n", 0);
			}		
		}	
	}
	
	public String getHint() {
		String response = "There is no hint available at this time.";
		Hint current_hint = hints.get(current_hint_index); 
		
		if ( current_hint.getStatus() == Hint_Status.AVAILABLE ) {
			current_hint.setStatus( Hint_Status.REVEALED );
			response = current_hint.getText();
			current_hint_index++;
			
			int rise = current_hint.getMax_fan_cost() - current_hint.getMin_fan_cost();
			int run = current_hint.getMinutes_till_min_cost() - current_hint.getMinutes_till_available();
			double fan_loss_slope = (double) rise/run;
			
			int fan_rebate = (int) Math.round( fan_loss_slope*(elapsed_minutes - current_hint.getMinutes_till_available()) );
			int fan_loss = current_hint.getMax_fan_cost() - fan_rebate;
			fan_loss = Math.max(fan_loss, current_hint.getMin_fan_cost());
			
			int new_fan_worth = current_fan_worth - fan_loss;
			current_fan_worth = Math.max(new_fan_worth, 0);
			
			System.out.println(rise);
			System.out.println(run);
			System.out.println(fan_loss_slope);
			System.out.println(new_fan_worth);
			System.out.println(current_fan_worth);
		}
		
		return response;
	}
	
	
}
