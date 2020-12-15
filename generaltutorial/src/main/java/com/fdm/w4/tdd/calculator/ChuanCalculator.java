package com.fdm.w4.tdd.calculator;


public class ChuanCalculator extends IntCalculator {

	@Override
	double evaluate(String expression) {
		expression = expFormatter(expression);
		double result = parenthesesHandler(expression);
		return result;
	}

	String expFormatter(String expression) {
		expression = expression.replaceAll(AppConstants.PATTERN_MULTISPACE, "");
		expression = expression.replaceAll("\\++", AppConstants.PATTERN_SUM);
		expression = expression.replaceAll("\\-\\-", AppConstants.PATTERN_SUM);
		expression = expression.replaceAll("\\+\\-", AppConstants.PATTERN_SUBSTRACT);
		expression = expression.replaceAll("\\-\\+", AppConstants.PATTERN_SUBSTRACT);
		return expression;
	}

	double parenthesesHandler(String expression) {
		expression = expFormatter(expression);
		int parenthese_last_open_index = expression.lastIndexOf('(');
		if (parenthese_last_open_index < 0)
			return sumHandler(expression);
		int parenthese_first_close_after_last_open_index = expression.substring(parenthese_last_open_index).indexOf(')')
				+ parenthese_last_open_index;
		String expression_inside_parentheses = expression.substring(parenthese_last_open_index + 1,
				parenthese_first_close_after_last_open_index);
		String expression_left_parentheses = expression.substring(0, parenthese_last_open_index);
		String expression_right_parentheses = expression.substring(parenthese_first_close_after_last_open_index + 1);
		String result_inside_parentheses = Double.toString(sumHandler(expression_inside_parentheses));
		if (result_inside_parentheses.startsWith("-") && expression_right_parentheses.startsWith("^")) {
			String first_num_right_parentheses = expression_right_parentheses.startsWith("-")
					? expression_right_parentheses.substring(2).split(AppConstants.SPLITER_NUM)[0]
					: expression_right_parentheses.substring(1).split(AppConstants.SPLITER_NUM)[0];
			if (numberFormatter(first_num_right_parentheses) % 2 == 0)
				result_inside_parentheses = result_inside_parentheses.substring(1);
		}
		return parenthesesHandler(
				expression_left_parentheses + result_inside_parentheses + expression_right_parentheses);
	}

	double sumHandler(String expression) {
		String[] expression_array = expression.split(AppConstants.SPLITER_SUM);
		if (expression_array.length <= 1) {
			return substractionHandler(expression);
		}
		String expression_first = expression_array[0];
		String exprssion_remained = expression.substring(expression_first.length() + 1);
		return substractionHandler(expression_first) + sumHandler(exprssion_remained);
	}

	double substractionHandler(String expression) {
		String[] expression_array = expression.split(AppConstants.SPLITER_SUBSTRACT);
		if (expression_array.length <= 1) {
			return multiplicationHandler(expression);
		}
		String expression_last = expression_array[expression_array.length - 1];
		String expression_remained = expression.substring(0, expression.length() - expression_last.length() - 1);
		return substractionHandler(expression_remained) - multiplicationHandler(expression_last);
	}

	double multiplicationHandler(String expression) {
		String[] expression_array = expression.split(AppConstants.SPLITER_MULITPLY);
		if (expression_array.length <= 1) {
			return divisionHandler(expression);
		}
		String expression_first = expression_array[0];
		String expression_remained = expression.substring(expression_first.length() + 1);
		return divisionHandler(expression_first) * multiplicationHandler(expression_remained);
	}
	
	double divisionHandler(String expression) {
		String[] expression_array = expression.split(AppConstants.SPLITER_DIVIDE);
		if (expression_array.length <= 1) {
			return exponentHandler(expression);
		}
		String expression_last = expression_array[expression_array.length - 1];
		String expression_remained = expression.substring(0, expression.length() - expression_last.length() - 1);
		return  divisionHandler(expression_remained) / exponentHandler(expression_last);
	}

	double exponentHandler(String expression) {
		String[] expression_array = expression.split(AppConstants.SPLITER_EXPONENT);
		if (expression_array.length <= 1) {
			return numberFormatter(expression);
		}
		String element_first = expression_array[0];
		String expression_remained = expression.substring(element_first.length() + 1);
		if (numberFormatter(element_first) == 0)
			return 1;
		return exponentRecurser(numberFormatter(element_first), exponentHandler(expression_remained));
	}

	double exponentRecurser(double exponent_base, double exponent_power) {
		if (exponent_power > 1)
			return exponent_base * exponentRecurser(exponent_base, exponent_power - 1);
		else if (exponent_power <= 0)
			return (1 / exponent_base) * exponentRecurser(exponent_base, exponent_power + 1);
		else
			return exponent_base;
	}

	double numberFormatter(String numStr) {
		if (numStr.isEmpty())
			return 0;
		return Double.parseDouble(numStr);
	}
}