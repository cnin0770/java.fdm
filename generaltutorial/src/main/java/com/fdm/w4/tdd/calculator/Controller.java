package com.fdm.w4.tdd.calculator;

class Controller {	
	private IntView view;
	private IntValidator validator;
	private IntCalculator calculator;
	public Controller(IntView view, IntValidator validator, IntCalculator calculator) {
		super();
		this.view = view;
		this.validator = validator;
		this.calculator = calculator;
	}
	
	public void handle() {
		view.display(AppConstants.MESSAGE_WELCOME + AppConstants.MESSAGE_MANUAL);
		while (true)
		{
			String expression = view.getInput();
			if (expression.equals(AppConstants.APPID)) break;
			try {
				validator.validate(expression);
				view.display(calculator.evaluate(expression));
			} catch (ValidationException e) {
				view.display(e.getMessage());
			}
		}
	}
}
