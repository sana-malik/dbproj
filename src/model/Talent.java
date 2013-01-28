package model;

public class Talent {
	private String name;
	private String icon_file;
	private String description;
	
	public Talent(String name) {
		this.name = name;
		icon_file = "images/default_talent.gif";
		description = "default talent description.";
	}
	@Override
	public String toString() {
		return "Talent [name=" + name + "]";
	}


}
