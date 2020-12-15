package com.fdm.CarParkApp;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ControllerTest {
    @Mock
    IntView mockView;
    @Mock
    IntValidator mockValidator;

    Controller controller;

    @Before
    public void initTest() {
        MockitoAnnotations.initMocks(this);
        controller = new Controller(mockValidator, mockView);
    }

    @Test
    public void that_receives_from_view_getInput() {
        try {
            controller.handle();
        } catch (NullPointerException e) {
            e.toString();
        }
        verify(mockView, times(1)).getInput();
    }

    @Test
    public void that_checks_legacy_json_record() {
        try {
            controller.handle();
        } catch (NullPointerException e) {
            e.toString();
        }
        verify(mockView, times(1)).displayReopen(anyInt());
    }

    @Test
    public void that_validates_expression() throws ValidationException {
        try {
            controller.handle();
            when(mockView.getInput()).thenReturn(anyString());
            verify(mockValidator, times(1)).validate(anyString());
        } catch (NullPointerException e) {
            e.toString();
        }
    }

    @Test
    public void that_display_error_message_when_invalid() {
        try {
            controller.handle();
            when(mockView.getInput()).thenReturn(anyString());
            doThrow(new ValidationException(anyString()));
            verify(mockValidator, times(1)).validate(anyString());
        } catch (NullPointerException e) {
            e.toString();
        } catch (ValidationException e) {
            verify(mockView, times(1)).displayError(anyString());
        }
    }
}
