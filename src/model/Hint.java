package model;

public class Hint {
	
	private String text;
	
	private int minutes_till_open;
	private int minutes_till_free;
	
	private double cost_multiplier;
	
	private int fans_lost;

	public Hint(String text, int minutes_till_open, int minutes_till_free,	float cost_multiplier) {
		this.text = text;
		this.minutes_till_open = minutes_till_open;
		this.minutes_till_free = minutes_till_free;
		this.cost_multiplier = cost_multiplier;
		this.fans_lost = 0;
	}

	public Hint() {
		this.text = "HINT NOT FILLED";
		this.minutes_till_open = 0;
		this.minutes_till_free = 0;
		this.cost_multiplier = 1.0;
		this.fans_lost = 0;
	}
	
	public void unlock(int minutes_passed) {
		if (minutes_passed >= minutes_till_open) 
			fans_lost = (int)((minutes_passed - minutes_till_open) * cost_multiplier);
	}
}
