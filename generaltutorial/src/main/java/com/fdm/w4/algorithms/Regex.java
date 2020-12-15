package com.fdm.w4.algorithms;

import java.util.regex.Matcher; 
import java.util.regex.Pattern; 
  
class Regex {
	static void test() { 
        // Create a pattern to be searched 
        Pattern pattern = Pattern.compile("[a-z]+"); 
  
        // Search above pattern in "geeksforgeeks.org" 
        Matcher m = pattern.matcher("geeksf5242ks.org"); 
  
        // Print starting and ending indexes of the pattern 
        // in text 
        while (m.find()) 
            System.out.println("Pattern found from " + m.start() + 
                               " to " + (m.end()-1)); 
    } 
}