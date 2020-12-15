package com.fdm.w4.pension;

class View {
	protected void printEligible (Person person) {
		System.out.printf("%s %s is old enough already.\n", person.getFirstname(), person.getLastname());
	}
	
	protected void printIneligible(Person person) {
		System.out.printf("%s %s is not old enough yet.\n", person.getFirstname(), person.getLastname());
	}
}