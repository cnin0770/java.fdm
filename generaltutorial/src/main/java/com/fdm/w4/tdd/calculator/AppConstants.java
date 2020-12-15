package com.fdm.w4.tdd.calculator;

import java.util.UUID;

class AppConstants {
	static final String APPID = UUID.randomUUID().toString();
	static final String EXITCODE = "exit";
	
	static final String MESSAGE_MANUAL = "use \"exit\" to terminate this session. ";
	static final String MESSAGE_WARNING = "[APP] " + MESSAGE_MANUAL;
	static final String MESSAGE_WELCOME = "Hey! ";
	static final String MESSAGE_BYE = "[APP] good bye. ";
	
	static final String EXCEPTION_PARENTHESES_ORDER = "parentheses should pair in order.";
	static final String EXCEPTION_PARENTHESES_QUANTITY = "parentheses should pair in quantity.";
	static final String EXCEPTION_LETTERS = "expression contains illegal characters.";
	static final String EXCEPTION_NULL = "expression cannot be null.";
	static final String EXCEPTION_OPERATOR = "invalid usage of operators [%c].";
	
	static final String PROMPT_USERINPUT = "[I/O] Enter expression: ";
	static final String PROMPT_ERROR = "[APP] %s\n";
	static final String PROMPT_RESULT = "[I/O] Result:\t\t%.2f\n";

	static final String PATTERN_REPEATING = ".*\\%c\\%c+.*";
	static final String PATTERN_MULTISPACE = "\\s+";
	static final String PATTERN_SUM = "\\+";
	static final String PATTERN_SUBSTRACT = "\\-";
	static final String PATTERN_EXPRESSION = "[\\d\\.e\\+\\-\\*\\/\\^\\(\\)]+";
	
	static final String SPLITER_SUM = "(?<!\\*|\\/|\\^)\\+";
	static final String SPLITER_SUBSTRACT = "(?<!\\*|\\/|\\^)\\-";
	static final String SPLITER_MULITPLY = "\\*";
	static final String SPLITER_DIVIDE = "\\/";
	static final String SPLITER_EXPONENT = "\\^";
	static final String SPLITER_NUM = "\\+|\\*|\\/|\\^|\\)|\\(";
	
	static final char[] NOREPEATABLEOPERATORS = {'+', '*', '/', '^', 'e'};
	static final char[] ALLOPERATORS = {'+', '-', '*', '/', '^', 'e'};
	
}
