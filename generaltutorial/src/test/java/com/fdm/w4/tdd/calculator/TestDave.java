package com.fdm.w4.tdd.calculator;

/*
import static org.junit.Assert.*;

import org.junit.Test;

public class DaveTest {

	ChCalculator calc = new ChCalculator();

	@Test
	public void testEmptyExpression() {
		assertEquals(0, calc.evaluate(""), 0);
	}

	@Test
	public void testOneExpression() {
		assertEquals(1, calc.evaluate("1"), 0);
		assertEquals(0, calc.evaluate("-0"), 0);
		assertEquals(1234.5678, calc.evaluate("1234.5678"), 0);
	}

	@Test
	public void testBasic() {
		assertEquals(3, calc.evaluate("9/3"), 0);
		assertEquals(4, calc.evaluate("1+3"), 0);
		assertEquals(-2, calc.evaluate("1-3"), 0);
		assertEquals(20, calc.evaluate("8*2.5"), 0);
	}

	@Test
	public void testTwoStepsCalc() {
		assertEquals(5, calc.evaluate("3+8-6"), 0);
		assertEquals(7, calc.evaluate("2*5-3"), 0);
		assertEquals(3, calc.evaluate("1*6/2"), 0);
		assertEquals(0.5, calc.evaluate("5/2-2"), 0);
		assertEquals(5, calc.evaluate("6-2/2"), 0);
		assertEquals(13, calc.evaluate("3+2*5"), 0);
		assertEquals(8, calc.evaluate("4*6/3"), 0);
		assertEquals(0, calc.evaluate("10-5-5"), 0);
		assertEquals(1, calc.evaluate("25/5/5"), 0);
	}

	@Test
	public void testMoreThanTwoSteps() {
		assertEquals(-2, calc.evaluate("5*5/5-7"), 0);
		assertEquals(17, calc.evaluate("7+5*12/6"), 0);
		assertEquals(-11, calc.evaluate("2*8-3*9"), 0);
		assertEquals(-5, calc.evaluate("10-5-5-5"), 0);
		assertEquals(5, calc.evaluate("10-5+5-5"), 0);
		assertEquals(1, calc.evaluate("10/5-5/5"), 0);
		assertEquals(3, calc.evaluate("25/5*3/5"), 0);
		// 5 - 3 + 32 - 9
		assertEquals(25, calc.evaluate("5-3*5/5+2*4/2*8-3*3"), 0);
	}

	@Test
	public void testPositiveNegative() {
		assertEquals(5, calc.evaluate("+5"), 0);
		assertEquals(10, calc.evaluate("+++++5++++5"), 0);
		assertEquals(-0.05, calc.evaluate("-0.05"), 0);
		assertEquals(-4, calc.evaluate("-6+2"), 0);
		assertEquals(-4, calc.evaluate("2+-6"), 0);
		assertEquals(8, calc.evaluate("2--6"), 0);
		assertEquals(-4, calc.evaluate("2---6"), 0);
		assertEquals(-4, calc.evaluate("------2-----6"), 0);
		assertEquals(-8, calc.evaluate("-2-6"), 0);
		assertEquals(-2, calc.evaluate("4/-2"), 0);
		assertEquals(2, calc.evaluate("-4/-2"), 0);
		assertEquals(4, calc.evaluate("-2*-2"), 0);
		assertEquals(15, calc.evaluate("++3*+++5"), 0);
		assertEquals(15, calc.evaluate("-3*-5"), 0);
		assertEquals(-15, calc.evaluate("-3*----5"), 0);
	}

	@Test
	public void testOneBracket() {
		assertEquals(0.01, calc.evaluate("(0.01)"), 0);
		assertEquals(-0.01, calc.evaluate("-(0.01)"), 0);
		assertEquals(2, calc.evaluate("(1+1)"), 0);
		assertEquals(9, calc.evaluate("(1+2*4)"), 0);
		assertEquals(14, calc.evaluate("2*(3+4)"), 0);
		assertEquals(3, calc.evaluate("-1*(-3)"), 0);
	}

	@Test
	public void testMultipleBrackets() {
		assertEquals(1, calc.evaluate("((1))"), 0);
		assertEquals(4, calc.evaluate("(1+1+(1+1))"), 0);
		assertEquals(6, calc.evaluate("(3*(1+1))"), 0);
		assertEquals(10, calc.evaluate("((2+3)*(1+1))"), 0);
		assertEquals(10, calc.evaluate("((1+2)+(3+4))"), 0);
		assertEquals(4, calc.evaluate("(-(1+2)+(3+4))"), 0);
		assertEquals(-4, calc.evaluate("-(-(1+2)+(3+4))"), 0);
		assertEquals(14, calc.evaluate("(((4+2)/3)*(3+4))"), 0);
		assertEquals(-1, calc.evaluate("-(-(-(1)))"), 0);
	}

	@Test
	public void testExponents() {
		assertEquals(1, calc.evaluate("0.5^0"), 0);
		assertEquals(8, calc.evaluate("2^3"), 0);
		assertEquals(-16, calc.evaluate("-4^2"), 0);
		assertEquals(-8, calc.evaluate("-2^3"), 0);
		assertEquals(55, calc.evaluate("1+2*3^3"), 0);
		assertEquals(512, calc.evaluate("2^3^2"), 0);
		assertEquals(-65280, calc.evaluate("4^2^2-2^4^2"), 0);
		assertEquals(-512, calc.evaluate("-2^3^2"), 0);
		assertEquals(72, calc.evaluate("2^3*3^2"), 0);
	}

	@Test
	public void testNegativeExponents() {
		assertEquals(0.25, calc.evaluate("2^-2"), 0);
		assertEquals(-9, calc.evaluate("-3^2"), 0);
		assertEquals(-0.25, calc.evaluate("-2^-2"), 0);
		assertEquals(1, calc.evaluate("2^-0"), 0);
		assertEquals(-0.0625, calc.evaluate("-2^-2^2"), 0);
	}

	@Test
	public void testBracketExponents() {
		assertEquals(27, calc.evaluate("3^(1+3-1)"), 0);
		assertEquals(9, calc.evaluate("(-3)^2"), 0);
		assertEquals(9, calc.evaluate("((-3)^2)"), 0);
		assertEquals(94, calc.evaluate("((-3)^(2*2) + (-4)^2-3)"), 0);
	}

	@Test
	public void testAll() {
		assertEquals(64, calc.evaluate("((-2^3)^2)"), 0);
		assertEquals(3, calc.evaluate("0.5^2*3/0.25"), 0);
		assertEquals(75, calc.evaluate("0.5^2*3/0.01"), 0);
		assertEquals(6561, calc.evaluate("((3)^(2))^(4)"), 0);
		assertEquals(-8, calc.evaluate("6*(8-9)-2"), 0);
		assertEquals(43046713, calc.evaluate("-8+(3)^((2)^(4))"), 0);
	}

}
*/