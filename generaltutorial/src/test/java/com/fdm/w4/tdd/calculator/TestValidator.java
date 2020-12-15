package com.fdm.w4.tdd.calculator;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestValidator {

	IntValidator validator = new Validator();
	
//	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test(expected = ValidationException.class)
	public void null_expression_is_invalid() throws ValidationException {
		String expression = null;
		validator.validate(expression);
	}
	
	@Test
	public void empty_is_valid() throws ValidationException {
		String expression = "";
		validator.validate(expression);
	}
	
	@Test
	public void number_is_valid() throws ValidationException {
		String expression = ".25";
		validator.validate(expression);
	}
	
	@Test(expected = ValidationException.class)
	public void letters_invalid() throws ValidationException {
		String expression = "dda";
		validator.validate(expression);
	}
	
	@Test
	public void scientific_express_is_valid() throws ValidationException {
		String expression = "1e10";
		validator.validate(expression);
	}

	@Test(expected = ValidationException.class)
	public void parentheses_should_pair() throws ValidationException {
		String expression = ")100";
		validator.validate(expression);
	}
	
	@Test(expected = ValidationException.class)
	public void parentheses_should_pair_in_quantity() throws ValidationException {
		String expression = ")100(";
		validator.validate(expression);
	}
	
	@Test(expected = ValidationException.class)
	public void parentheses_should_pair_in_order() throws ValidationException {
		String expression = "(100)+)((3)";
		validator.validate(expression);
	}
	
	@Test(expected = ValidationException.class)
	public void avoid_duplicate_operators() throws ValidationException {
		String expression = "100*3";
		validator.validate(expression);
		expression = "100/3";
		validator.validate(expression);
		expression = "100+++3";
		validator.validate(expression);
		expression = "100--3"; //??
		validator.validate(expression);
		/*
		try {
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		*/
	}
}
