package com.fdm.w4.tdd.calculator;

abstract class IntCalculator {
	
	/**
	 * Interface to implement a calculator
	 * @param expression String expression to be evaluated
	 * @return result double evaluated
	 */
	abstract double evaluate(String expression);
}
