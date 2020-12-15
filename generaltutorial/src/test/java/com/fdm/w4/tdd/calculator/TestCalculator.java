package com.fdm.w4.tdd.calculator;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Random;

public class TestCalculator {
	ChuanCalculator cal = new ChuanCalculator();

	public static double randomDoubleGenerator() {
		Random rd = new Random();
		return rd.nextDouble() * 100;
	}

	@Test
	public void empty_returns_zero() {
		String ins = "";
		double result = cal.evaluate(ins);
		assertEquals(0, result, .01);
	}

	@Test
	public void one_returns_one() {
		String ins = "1";
		double result = cal.evaluate(ins);
		assertEquals(1, result, .01);
	}

	@Test
	public void point_one_returns_point_one() {
		String ins = ".1";
		double result = cal.evaluate(ins);
		assertEquals(.1, result, .01);
	}

	@Test
	public void point_zerozerozeroone_returns_point_zerozerozeroone() {
		String ins = ".0001";
		double result = cal.evaluate(ins);
		assertEquals(.0001, result, .01);
	}

	@Test
	public void plus_one_returns_one() {
		String ins = "+1";
		double result = cal.evaluate(ins);
		assertEquals(1, result, .01);
	}

	@Test
	public void minus_one_returns_minus_one() {
		String ins = "-1";
		double result = cal.evaluate(ins);
		assertEquals(-1, result, .01);
	}

	@Test
	public void minus_one_plus_two_returns_one() {
		String ins = "-1+2";
		double result = cal.evaluate(ins);
		assertEquals(1, result, .01);
	}

	@Test
	public void plus_minus_sign_off() {
		String ins = "-10+20-30+40-50+60.75";
		double result = cal.evaluate(ins);
		assertEquals(30.75, result, .01);
	}

	@Test
	public void minus_one_times_two_returns_minus_two() {
		String ins = "-1 * 2";
		double result = cal.evaluate(ins);
		assertEquals(-2, result, .01);
	}

	@Test
	public void plus_minus_multi_divide_sign_off() {
		String ins = "-1 +3*4/8";
		double result = cal.evaluate(ins);
		assertEquals(.5, result, .01);
	}

	@Test
	public void bracket_empty_returns_zero() {
		String ins = "()";
		double result = cal.evaluate(ins);
		assertEquals(0, result, .01);
	}

	@Test
	public void bracket_minus_one_bracket_plus_two_returns_one() {
		String ins = "(-1)+2";
		double result = cal.evaluate(ins);
		assertEquals(1, result, .01);
	}

	@Test
	public void brackets_sign_off() {
		String ins = "-1+2*3-((5+3)/4+2)";
		double result = cal.evaluate(ins);
		assertEquals(1, result, .01);
	}

	@Test
	public void matt_s_ultimate_bracket_sign_off() {
		String ins = "((1+2)+(3+4^2))";
		double result = cal.evaluate(ins);
		assertEquals(22, result, .01);
	}

	@Test
	public void minus_one_minus_minus_one_returns_nada() {
		String ins = "-1--1";
		double result = cal.evaluate(ins);
		assertEquals(0, result, .01);
	}

	@Test
	public void minus_minus_minus_one_returns_minus_one() {
		String ins = "---1";
		double result = cal.evaluate(ins);
		assertEquals(-1, result, .01);
	}

	@Test
	public void bracket_bracket_minus_one_bracket_times_minus_one_bracket_returns_one() {
		String ins = "-1+-3";
		double result = cal.evaluate(ins);
		assertEquals(-4, result, .01);
	}

	@Test
	public void reverse_test() {
		String ins = "44/-4";
		double result = cal.evaluate(ins);
		assertEquals(-11, result, .01);
	}

	@Test
	public void minus_use_case() {
		String ins = "(-3*-1)-4-3";
		double result = cal.evaluate(ins);
		assertEquals(-4, result, .01);
	}

	@Test
	public void pow_sign_off() {
		String ins = "2^-3";
		double result = cal.evaluate(ins);
		assertEquals(.125, result, .01);
	}

	@Test
	public void toms_test() {
		String ins = "2*((3+2)/(1))";
		double result = cal.evaluate(ins);
		assertEquals(10, result, .01);
	}

//	@Test
	public void two_powed_minus_two_powed_two_returns_pointzerosixtwofive() {
		String ins = "-2^-2^2";
		double result = cal.evaluate(ins);
		assertEquals(-.0625, result, .01);
	}

	@Test
	public void minus_exponent() {
		String ins = "(-2)^(1-3)^2";
		double result = cal.evaluate(ins);
		assertEquals(16, result, .01);
	}

	@Test
	public void minus_exponent_positive_compare() {
		String ins = "4^2^2-2^4^2";
		double result = cal.evaluate(ins);
		assertEquals(-65280, result, .01);
	}

	@Test
	public void minus_exponent_positive() {
		String ins = "(-2)^(2+2)^2";
		double result = cal.evaluate(ins);
		assertEquals(65536, result, .01);
	}
	
	@Test
	public void large_num() {
		String ins = "3^2^1";
		double result = cal.evaluate(ins);
		assertEquals(9, result, .01);
	}

	@Test
	public void all_sign_off() {
		String ins = "((-1+2^2-(4+8)*.25))*(+-1) + (-2)^2";
		double result = cal.evaluate(ins);
		assertEquals(4, result, .01);
	}
}