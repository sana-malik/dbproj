package model;

public class PartialAnswer {
	private String answer;
	private String response;
	
	private boolean requires_exact_match;

	public PartialAnswer(String answer, String response, boolean requires_exact_match) {
		this.answer = answer;
		this.response = response;
		this.requires_exact_match = requires_exact_match;
	}
	
	public PartialAnswer(String answer, String response) {
		this(answer, response, true);
	}
	
	public PartialAnswer() {
		this("", "Unrecognized response.", false);
	}
	
}
