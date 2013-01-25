package model;

import java.util.ArrayList;
import java.util.Date;

public class Location {
	private ArrayList<Puzzle> puzzle_list;
	private String address;
	
	private String notes;
	private String restroom_description;
	private String food_description;
	
	private String image_file;
	private String map_file;
	
	private Date time_open;
	private Date time_closed;
	
	public Location(ArrayList<Puzzle> puzzle_list, String address,
			String notes, String restroom_description, String food_description,
			String image_file, String map_file, Date time_open, Date time_closed) {
		super();
		this.puzzle_list = puzzle_list;
		this.address = address;
		this.notes = notes;
		this.restroom_description = restroom_description;
		this.food_description = food_description;
		this.image_file = image_file;
		this.map_file = map_file;
		this.time_open = time_open;
		this.time_closed = time_closed;
	}
}
