package com.fdm.w4.pension;

class Person {
	private String firstname, lastname, dateOfBirth;
	
	protected Person(String firstname, String lastname, String dateOfBirth) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.dateOfBirth = dateOfBirth;
	}

	protected String getFirstname() {
		return firstname;
	}

	protected void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	protected String getLastname() {
		return lastname;
	}

	protected void setLastname(String lastname) {
		this.lastname = lastname;
	}

	protected String getdateOfBirth() {
		return dateOfBirth;
	}

	protected void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
}
