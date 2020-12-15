package com.fdm.w4.tdd.calculator;

class CalculatorApp {

	public static void main(String[] args) {
		View view = new View();
		Validator validator = new Validator();
		ChuanCalculator calc = new ChuanCalculator();
		Controller app = new Controller(view, validator, calc);
		
		app.handle();
	}
}
