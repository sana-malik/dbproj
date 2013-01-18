package model;

import java.sql.Time;
import java.util.ArrayList;

public class Location {
	private ArrayList<Puzzle> puzzle_list;
	private String address;
	
	private String notes;
	private String restroom_description;
	private String food_description;
	
	private String image_file;
	private String map_file;
	
	private Time time_open;
	private Time time_closed;
}
