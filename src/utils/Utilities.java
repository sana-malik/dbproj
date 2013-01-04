package utils;

public class Utilities {
	
	public static String normalizeText(String text) {
		String normal = text.toLowerCase();
		normal.replaceAll("\\W", "");  // remove punctuation and spaces
		return normal;
	}
}
