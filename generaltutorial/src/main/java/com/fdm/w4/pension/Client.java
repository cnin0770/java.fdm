package com.fdm.w4.pension;

public class Client {

	public static void main(String[] args) {

		Person[] people = {
				new Person("John", "Smith", "28/06/1993"), 
				new Person("Jane", "Doe", "05/01/1950"), 
				new Person("Fred", "Bloggs", "12/12/1949")
				};
		
		PensionController ctrl = new PensionController(people);
		ctrl.handlePensions();
	}

}
