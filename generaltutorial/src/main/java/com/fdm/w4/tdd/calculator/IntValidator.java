package com.fdm.w4.tdd.calculator;

interface IntValidator {
	
	/**
	 * to implement a validator for expression
	 * @param expression to be validated
	 * @throws ValidationException that generates through validation
	 */
	void validate(String expression) throws ValidationException;
}
