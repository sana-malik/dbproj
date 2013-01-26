package utilities;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import model.Answer;
import model.Hint;
import model.Location;
import model.Puzzle;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class XMLReader {

	public static Location readLocation(String filename) {
		Location location = null;
		
		ArrayList<Puzzle> puzzle_list = new ArrayList<Puzzle>();
		String address;
		
		String notes;
		String restroom_description;
		String food_description;
		
		String image_file;
		String map_file;
		
		Date time_open;
		Date time_closed;

		try {

			File fXmlFile = new File(Const.LOCATION_DIR + filename);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			if ( !doc.getDocumentElement().getNodeName().equals( "location" ) ) {
				System.err.println("Attempting to read location from a non-location file.");
			}
			else {
				SimpleDateFormat df = new SimpleDateFormat("h:mm a");
				Element eElement = doc.getDocumentElement();
				
				address =  getTagString("address", eElement);
				
				notes = getTagString("notes", eElement);
				restroom_description = getTagString("restroom_description", eElement);
				food_description = getTagString("food_description", eElement);
				
				image_file = getTagString("time_closed", eElement);
				map_file = getTagString("time_closed", eElement);
				
				
				time_open = df.parse( getTagString("time_open", eElement) );
				time_closed = df.parse( getTagString("time_closed", eElement) );
				
				
				NodeList nList = doc.getElementsByTagName("puzzle");
				
				for (int temp = 0; temp < nList.getLength(); temp++) {
					String puzz_file = nList.item( temp ).getFirstChild().getNodeValue();
					
					puzzle_list.add( readPuzzle( puzz_file ) ); 
				}
				
				location = new Location(puzzle_list, address, notes, restroom_description, food_description, image_file, map_file, time_open, time_closed);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return location;
	}

	public static Puzzle readPuzzle(String filename) {
		Puzzle puzzle = null;
		
		String name;
		String start_code;
		String flavor_text;
		
		int max_fan_worth;
		int par_time;
		
		ArrayList<Hint> hints;
		ArrayList<Answer> answers;
		ArrayList<String> resources_unlocked;
		ArrayList<String> teams_killed;

		try {

			File fXmlFile = new File(Const.PUZZLE_DIR + filename);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			if ( !doc.getDocumentElement().getNodeName().equals( "puzzle" ) ) {
				System.err.println("Attempting to read puzzle from a non-puzzle file.");
			}
			else {
				Element eElement = doc.getDocumentElement();
				
				name = getTagString("name", eElement);
				start_code = getTagString("start_code", eElement);
				flavor_text = getTagString("flavor_text", eElement);
				
				max_fan_worth = getTagInt("max_fan_worth", eElement);
				par_time = getTagInt("par_time", eElement);
				
				hints = getHintList( doc );
				answers = getAnswerList( doc );
				resources_unlocked = getResourceList( doc );
				teams_killed = getKillList( doc );
				
				puzzle = new Puzzle(name, start_code, flavor_text, max_fan_worth, par_time, hints, answers, resources_unlocked, teams_killed);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return puzzle;
	}
	
	public static ArrayList<String> getKillList ( Document doc ) {
		ArrayList<String> kills = new ArrayList<String>();
		
		NodeList nList = doc.getElementsByTagName("team_killed");
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			kills.add( nList.item(temp).getNodeValue() );
		}
		
		return kills;
	}
	
	public static ArrayList<String> getResourceList ( Document doc ) {
		ArrayList<String> resources = new ArrayList<String>();
		
		NodeList nList = doc.getElementsByTagName("resource_unlocked");
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			resources.add( nList.item(temp).getNodeValue() );
		}
		
		return resources;
	}
	
	public static ArrayList<Hint> getHintList ( Document doc ) {
		ArrayList<Hint> hints = new ArrayList<Hint>();
		
		NodeList nList = doc.getElementsByTagName("hint");
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			hints.add( readHint( nList.item(temp) ) );
		}
		
		return hints;
	}
	
	public static Hint readHint(Node hint_node) {
		Hint hint = null;

		if (hint_node.getNodeType() == Node.ELEMENT_NODE) {
		
			Element eElement = (Element) hint_node;
			
			String text = getTagString("text", eElement);
			int minutes_till_available = getTagInt("minutes_till_available", eElement);
			int minutes_till_min_cost = getTagInt("minutes_till_min_cost", eElement);
			int max_fan_cost = getTagInt("max_fan_cost", eElement);
			int min_fan_cost = getTagInt("min_fan_cost", eElement);

			hint = new Hint(text, minutes_till_available, minutes_till_min_cost, max_fan_cost, min_fan_cost);
		}

		return hint;
	}
	
	public static ArrayList<Answer> getAnswerList ( Document doc ) {
		ArrayList<Answer> answers = new ArrayList<Answer>();
		
		NodeList nList = doc.getElementsByTagName("answer");
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			answers.add( readAnswer( nList.item(temp) ) );
		}
		
		return answers;
	}
	
	public static Answer readAnswer(Node ans_node) {
		Answer ans = null;

		if (ans_node.getNodeType() == Node.ELEMENT_NODE) {
		
			Element eElement = (Element) ans_node;
			
			String text = getTagString("text", eElement);
			String type = getTagString("type", eElement);
			String response = getTagString("response", eElement);
			int hint_jump = getTagInt("min_fan_cost", eElement);

			ans = new Answer(text, type, response, hint_jump);
		}

		return ans;
	}

	private static String getTagString(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();

		Node nValue = (Node) nlList.item(0);

		return nValue.getNodeValue();
	}

	private static int getTagInt(String sTag, Element eElement) {
		int value = -1;
		
		NodeList tagList = eElement.getElementsByTagName(sTag);

		if( tagList.getLength() != 0 ){
			NodeList nlList = tagList.item(0).getChildNodes();
			Node nValue = (Node) nlList.item(0);
			
			value = Integer.parseInt( nValue.getNodeValue().trim() );
		}
		
		return value;
	}
}