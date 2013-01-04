package utils;

import java.io.*;

public class Utilities {
	
	public static String normalizeText(String text) {
		String normal = text.toLowerCase();
		normal.replaceAll("\\W", "");  // remove punctuation and spaces
		return normal;
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
