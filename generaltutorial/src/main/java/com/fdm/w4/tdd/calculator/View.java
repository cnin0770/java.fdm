package com.fdm.w4.tdd.calculator;

import java.util.Scanner;

class View implements IntView {
	Scanner scanner = new Scanner(System.in); 

	public String getInput() {
		return scanning();
	}

	public void display(String message) {
		System.out.printf(AppConstants.PROMPT_ERROR, message);
	}


	public void display(double result) {
		System.out.printf(AppConstants.PROMPT_RESULT, result);
	}
	
	private String scanning() {
		String expression = AppConstants.APPID;
		System.out.printf(AppConstants.PROMPT_USERINPUT);
		try {
			expression = scanner.nextLine();
		}
		catch (Exception e) {
			e.toString();
		}
		finally {
			if (expression.isEmpty()) System.out.println(AppConstants.MESSAGE_WARNING);
			if (expression.toLowerCase().equals(AppConstants.EXITCODE)) {
				System.out.println(AppConstants.MESSAGE_BYE);
				System.exit(0);
			}
		}
		return expression;
	}

}
