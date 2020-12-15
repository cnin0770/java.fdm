package com.fdm.w4.pension;

class PensionLogic {
	boolean isPensionable(Person person, String year) {
		final int RETIREAGE = 65;
		int birthYear = Integer.parseInt(person.getdateOfBirth().split("/")[2]);

		return Integer.parseInt(year) - birthYear >= RETIREAGE;
	}
}
