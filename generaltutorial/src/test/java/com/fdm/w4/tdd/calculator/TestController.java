package com.fdm.w4.tdd.calculator;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TestController {

	static final String GREETING = "hey";
	@Mock
	IntCalculator mockCalc;
	@Mock
	IntView mockView;
	@Mock
	IntValidator mockValidator;
	
	Controller controller;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
		controller = new Controller(mockView, mockValidator, mockCalc);
	}
	
	@Test
	public void welcome_message_is_displayed() throws ValidationException {
		// arrange
		/* arrange code */
		// act
		controller.handle();
		// assert
		verify(mockView, times(1)).display(TestController.GREETING);
	}
	
	@Test
	public void input_is_received() throws ValidationException {
		controller.handle();
		verify(mockView, times(1)).getInput();
	}
	
	@Test
	public void validation_occurs() throws ValidationException {
		String expression = "1+2";
		when(mockView.getInput()).thenReturn(expression);
		controller.handle();
		verify(mockValidator, times(1)).validate(expression);
	}
	
	@Test
	public void invalidate_input_cause_exception_with_reason_displayed() throws ValidationException {
		String expression_not_matter = "aaaaaaa";
		String mock_exception_message = "invalid";
		when(mockView.getInput()).thenReturn(expression_not_matter);
		doThrow(new ValidationException(mock_exception_message)).when(mockValidator).validate(expression_not_matter);
		controller.handle();
		verify(mockView, times(1)).display(mock_exception_message);
	}
	
	@Test
	public void valid_input_is_evaluated() throws ValidationException {
		String expression = "aa";
		when(mockView.getInput()).thenReturn(expression);
		controller.handle();
		verify(mockCalc, times(1)).evaluate(expression);	
	}
	
	@Test
	public void result_is_displayed() {
		String expression = "aa";
		when(mockView.getInput()).thenReturn(expression);
		controller.handle();
		verify(mockView, times(1)).display(0);
	}
	
	// ???
	@Test
	public void result_pass_through_view() {
//		String message = "boom";
//		when(mockView.getInput()).thenReturn("22");
//		doThrow(new ValidationException(message)).when(mockValidator).validate("22");
		when(mockCalc.evaluate("22")).thenReturn((double) 5);
		controller.handle();
		verify(mockView, times(1)).display(0);
	}
	
	@Test
	public void invalid_input_is_not_evaluated() throws ValidationException {
		String expression = "aa";
		String mock_exception_message = "invalid";
		when(mockView.getInput()).thenReturn(expression);
		doThrow(new ValidationException(mock_exception_message)).when(mockValidator).validate(expression);
		controller.handle();
		verify(mockCalc, never()).evaluate(expression);
	}
	
}
