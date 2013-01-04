package model;

import java.util.ArrayList;

public class Puzzle {
	int ID;
	String intro_text;
	
	private ArrayList<Hint> hints;
	private ArrayList<PartialAnswer> part_answers;
	
	String answer;
	
	private int full_fan_worth;
}
