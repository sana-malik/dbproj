package model;

public class Hint {
	
	private String text;
	
	private int minutes_till_available; 
	private int minutes_till_min_cost;
	
	private int index;
	
	private int max_fan_cost;
	private int min_fan_cost;
	
	
	public Hint(String text, int minutes_till_available,
			int minutes_till_min_cost, int max_fan_cost,
			int min_fan_cost) {
		super();
		this.text = text;
		this.minutes_till_available = minutes_till_available;
		this.minutes_till_min_cost = minutes_till_min_cost;
		this.max_fan_cost = max_fan_cost;
		this.min_fan_cost = min_fan_cost;
	}


	@Override
	public String toString() {
		return "Hint [text=" + text + ", minutes_till_available="
				+ minutes_till_available + ", minutes_till_min_cost="
				+ minutes_till_min_cost + ", index=" + index
				+ ", max_fan_cost=" + max_fan_cost + ", min_fan_cost="
				+ min_fan_cost + "]";
	}
	
	
	
}
