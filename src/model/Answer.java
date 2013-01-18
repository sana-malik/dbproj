package model;

import utils.Utilities;

public class Answer {
	private String text;
	private String response;
	// Our notes say that actionmethod should go here, but I can't remember what that is.
	private int hint_jump;
	private String type;
	
	public Answer(String text, String response, String type, int hint_jump) {
		this.text = text;
		this.response = response;
		this.hint_jump = hint_jump;
		this.type = type;
	}

	public Answer(String text, String response, String type) {
		this(text, response, type, -1);
	}
	
	public Answer() {
		this("", "Unrecognized response.", "default", -1);
	}
	
	public boolean checkAnswer(String answer) {
		return Utilities.normalizeText( answer ).equals( text );
	}
	
	public String getResponse() {
		return response;
	}
	
	public String getType() {
		return type;
	}
	
	public int getHintJump() {
		return hint_jump;
	}
}
