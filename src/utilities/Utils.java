package utilities;

import java.io.*;

public class Utils {
	
	public static String normalizeText(String text) {
		return text.toLowerCase().replaceAll("\\W", "");  // remove punctuation and spaces
	}
	
	// Serializes and saves an object
	public static void saveObject( Object obj, String filename ) {
		try {
			FileOutputStream f = new FileOutputStream( filename );
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject( obj );
			o.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// Loads a serialized object
	public static Object loadObject( String filename ) {
		try {
			FileInputStream f = new FileInputStream( filename );
			ObjectInputStream o = new ObjectInputStream(f);
			return o.readObject();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
}
