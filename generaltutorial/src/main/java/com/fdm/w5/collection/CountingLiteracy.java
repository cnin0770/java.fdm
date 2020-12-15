package com.fdm.w5.collection;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

class CountingLiteracy {	
	static Scanner scanner = new Scanner(System.in);
	
	static Map<Character, Integer> countingLetters(String expression) {
		Map<Character, Integer> counting = new TreeMap<Character, Integer>();

		if (expression.isEmpty()) {
			System.out.println("enter expression: ");
			expression = scanner.nextLine();
		}
		expression = expression.replaceAll("\\s+", "");
		for (char i: expression.toCharArray()) {
			i = Character.toLowerCase(i);
			if (counting.get(i) == null) counting.put(i, 1);
			else counting.put(i, counting.get(i) + 1);
		}
		return counting;
	}
	
	static Map<String, Integer> countingWords(String expression) {
		Map<String, Integer> counting = new TreeMap<String, Integer>();
		
		if (expression.isEmpty()) {
			System.out.println("enter expression: ");
			expression = scanner.nextLine();
		}
		expression = expression.replaceAll("[^a-zA-Z\\s]", "");
		for (String i: expression.split("\\s+")) {
			i = i.toLowerCase();
			if (counting.get(i) == null) counting.put(i, 1);
			else counting.put(i, counting.get(i) + 1);
		}
		return counting;
	}
	
	static LinkedList<Integer> reverseList(LinkedList<Integer> origin){
		LinkedList<Integer> reversed_list = new LinkedList<Integer>();
		int len = origin.size();
		for (int i = 0; i < len; i++) reversed_list.add(origin.removeLast());
		return reversed_list;
	}
	
	static TreeSet<String> tokenizedText(String expression) {
		TreeSet<String> tokens = new TreeSet<String>();
		
		if (expression.isEmpty()) {
			System.out.println("enter expression: ");
			expression = scanner.nextLine();
		}
		
		for (String i: expression.split("\\s+")) {
			i = i.toLowerCase();
			tokens.add(i);
		}
		
		return (TreeSet<String>) tokens.descendingSet();
	}
	
	static PriorityQueue<Double> descendingNum(double[] nums) {
		DoubleComparator compara = new DoubleComparator();
		PriorityQueue<Double> result = new PriorityQueue<Double>(compara);
		for (double i: nums) result.add(i);
		
		return result;
	}
	
	private static class DoubleComparator implements Comparator<Double>{
		public int compare(Double o1, Double o2) {
			return (int)((o2 - o1)*1000);
		}
	}
}
