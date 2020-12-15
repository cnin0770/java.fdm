package com.fdm.CarParkApp;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorTest {
    IntValidator validator;

    @Before
    public void setPark() {
        validator = new Validator();
    }

    @Test
    public void do_throw_error_on_empty() {
        try {
            validator.validate("");
        } catch (ValidationException e) {
            assertTrue(e != null);
        }
    }

    @Test
    public void do_throw_error_on_invalid() {
        try {
            validator.validate("reporty");
        } catch (ValidationException e) {
            assertTrue(e != null);
        }
    }

    @Test
    public void no_error_thrown_on_valid() {
        try {
            validator.validate("report");
            validator.validate("new");
            validator.validate("quit");
            validator.validate("enter car");
            validator.validate("exit truck");
        } catch (ValidationException e) {
            assertNull(e);
        }
    }
}
