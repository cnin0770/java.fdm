package com.fdm.w4.tdd.calculator;

class Validator implements IntValidator {
	public void validate(String expression) throws ValidationException {
		if (expression == null)
			throw new ValidationException(AppConstants.EXCEPTION_NULL);
		
		if (!expression.matches(AppConstants.PATTERN_EXPRESSION))
			throw new ValidationException(AppConstants.EXCEPTION_LETTERS);

		if (expression.length() - expression.replace("(", "").length() != expression.length()
				- expression.replace(")", "").length())
			throw new ValidationException(AppConstants.EXCEPTION_PARENTHESES_QUANTITY);

		if (!parenthesesInOrder(expression))
			throw new ValidationException(AppConstants.EXCEPTION_PARENTHESES_ORDER);
		
		for (char i : AppConstants.NOREPEATABLEOPERATORS)
			if (expression.matches(String.format(AppConstants.PATTERN_REPEATING,i,i)) || expression.startsWith(Character.toString(i)))
				throw new ValidationException(String.format(AppConstants.EXCEPTION_OPERATOR, i));
		
		for (char i : AppConstants.ALLOPERATORS)
			if (expression.endsWith(Character.toString(i)))
				throw new ValidationException(String.format(AppConstants.EXCEPTION_OPERATOR, i));
	}

	private boolean parenthesesInOrder(String expression) {
		expression = expression.replaceAll("[^(|)]", "");
		if (expression.isEmpty())
			return true;
		if (expression.charAt(0) == ')')
			return false;
		String result = "";
		for (char i : expression.toCharArray()) {
			if (i == '(')
				result = result + i;
			if (i == ')') {
				if (result.isEmpty())
					return false;
				else
					result = result.substring(0, result.length() - 1);
			}
		}
		return (result.isEmpty());
	}
}
