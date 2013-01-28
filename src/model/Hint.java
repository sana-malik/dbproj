package model;

import utilities.Const.Hint_Status;

public class Hint {
	
	private String text;
	
	private int minutes_till_available; 
	private int minutes_till_min_cost;
	
	private int index;
	
	private int max_fan_cost;
	private int min_fan_cost;
	
	private Hint_Status status;
	
	public Hint(String text, int minutes_till_available,
			int minutes_till_min_cost, int max_fan_cost,
			int min_fan_cost) {
		super();
		this.text = text;
		this.minutes_till_available = minutes_till_available;
		this.minutes_till_min_cost = minutes_till_min_cost;
		this.max_fan_cost = max_fan_cost;
		this.min_fan_cost = min_fan_cost;
		
		status = Hint_Status.LOCKED;
	}
	
	@Override
	public String toString() {
		return "Hint [text=" + text + ", minutes_till_available="
				+ minutes_till_available + ", minutes_till_min_cost="
				+ minutes_till_min_cost + ", index=" + index
				+ ", max_fan_cost=" + max_fan_cost + ", min_fan_cost="
				+ min_fan_cost + "]";
	}


	public String getText() {
		return text;
	}


	public int getMinutes_till_available() {
		return minutes_till_available;
	}


	public int getMinutes_till_min_cost() {
		return minutes_till_min_cost;
	}


	public int getMax_fan_cost() {
		return max_fan_cost;
	}


	public int getMin_fan_cost() {
		return min_fan_cost;
	}

	public void setStatus(Hint_Status status) {
		this.status = status;
	}


	public Hint_Status getStatus() {
		return status;
	}	
}
