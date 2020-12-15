package com.fdm.w4.pension;

class PensionController {
	private Person[] people;
	private String thisYear = "2014";
	
	PensionController(Person[] people) {
		this.people = people;
	}

	void handlePensions() {
		for (Person person: people) 
		{
			PensionLogic onelogic = new PensionLogic();
			View oneView = new View();
			if (onelogic.isPensionable(person, thisYear)) {
				oneView.printEligible(person);
			} 
			else {
				oneView.printIneligible(person);
			}
		}
	}
}
