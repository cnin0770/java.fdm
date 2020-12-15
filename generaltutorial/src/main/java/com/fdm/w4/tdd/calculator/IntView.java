package com.fdm.w4.tdd.calculator;

interface IntView {
	/**
	 * @return expression from user input
	 */
	String getInput();
	void display(double result);
	void display(String message);
}
