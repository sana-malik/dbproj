package model;

import utilities.Utils;

public class Answer {
	private String type;
	private String text;
	private String response;
	// Our notes say that actionmethod should go here, but I can't remember what that is.
	private int hint_jump;
	
	
	public Answer(String text, String type, String response, int hint_jump) {
		this.text = Utils.normalizeText( text );
		this.type = type;
		this.response = response;
		this.hint_jump = hint_jump;
		
	}

	public Answer(String text, String type, String response) {
		this(text, response, type, -1);
	}
	
	public Answer() {
		this("", "Unrecognized input: no matching entry found.", "default", -1);
	}
	
	public boolean checkAnswer(String answer) {
		return Utils.normalizeText( answer ).equals( text );
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

	@Override
	public String toString() {
		return "Answer [type=" + type + ", text=" + text + ", response="
				+ response + ", hint_jump=" + hint_jump + "]";
	}

	public boolean isFinal() {
		return type.equals("final");
	}
	
	
}
