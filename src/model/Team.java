package model;

import java.util.ArrayList;

public class Team {
	private String id;
	private String name;
	private String bio;
	private String mentor;
	
	private String type;	
	private int fans;
	private boolean dead;
	private ArrayList<Resource> resource_list;
	private ArrayList<Talent> talent_list;
	
	private String password;
	
	private String picture_file;
	private String death_vid_file;
	private String icon_alive_file;
	private String icon_dead_file;
	
	public Team(String id, String name, String bio, String mentor, String type,
			int fans, boolean dead, ArrayList<Resource> resource_list,
			ArrayList<Talent> talent_list, String password,
			String picture_file, String death_vid_file, String icon_alive_file,
			String icon_dead_file) {
		this.id = id;
		this.name = name;
		this.bio = bio;
		this.mentor = mentor;
		this.type = type;
		this.fans = fans;
		this.dead = dead;
		this.resource_list = resource_list;
		this.talent_list = talent_list;
		this.password = password;
		this.picture_file = picture_file;
		this.death_vid_file = death_vid_file;
		this.icon_alive_file = icon_alive_file;
		this.icon_dead_file = icon_dead_file;
	}
	
	public Team(String id) {
		this(   id, 
				"White Team", 
				"The White Team is made up of debate team nerds, led by Wesley (played by Eddie Deezen). The White Team rides matching Puch mopeds which they eventually share with the Red team after their vehicle is destroyed.",
				"Leon",
				"Casual",
				0,
				false,
				new ArrayList<Resource>(),
				new ArrayList<Talent>(),
				"password",
				"",
				"",
				"",
				""
			);
	}


	public String getId() {
		return id;
	}
	
	public String getIconAliveFile() {
		return icon_alive_file;
	}
	
}
