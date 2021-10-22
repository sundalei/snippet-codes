package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {
    
    public static void main(String[] args) {
    	
    	List<String> types = new ArrayList<>();
    	
    	types.add(".xml");
    	types.add(".txt");
    	types.add(".csv");
    	types.add(".xls");
    	types.add(".[A-Z][0-9][0-9]");
    	
    	String extension = ".xml";
    	
    	for (String type : types) {
    		String regex = "^\\." + type.substring(1) + "$";
    		Pattern pattern = Pattern.compile(regex);
    		Matcher matcher = pattern.matcher(extension);
    		if (matcher.matches()) {
    			System.out.println(extension + " matched " + type);
    			return;
    		}
    	}
    	
    	System.out.println(extension + " not matched.");
    }
}
