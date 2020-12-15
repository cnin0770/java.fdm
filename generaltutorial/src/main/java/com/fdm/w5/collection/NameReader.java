package com.fdm.w5.collection;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

class NameReader {
	static Scanner scanner = new Scanner(System.in);
	static Set<String> first_names = new TreeSet<String>();

	public static void main(String[] args) {
		System.out.println("enter names: ");
		while (true) {
			scanner();
		}
	}

	private static void scanner() {
		String expression = "";
		try {
			expression = scanner.nextLine();
			if (expression.toLowerCase().equals("exit")) {
				System.out.println("====");
				for (String i : first_names)
					System.out.println(i);
				System.exit(0);
			}
			first_names.add(expression);
		} catch (Exception e) {
			e.toString();
		}
	}
}
