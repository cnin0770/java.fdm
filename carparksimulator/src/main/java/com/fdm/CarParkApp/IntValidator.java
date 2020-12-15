package com.fdm.CarParkApp;

public interface IntValidator {
    void validate(String expression) throws ValidationException;
    void validate(int bad_operation_code) throws ValidationException;
}
