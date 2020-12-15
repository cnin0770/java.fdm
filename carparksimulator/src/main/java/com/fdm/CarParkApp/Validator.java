package com.fdm.CarParkApp;

import static com.fdm.CarParkApp.AppConstants.*;

class Validator implements IntValidator {
    public void validate(String expression) throws ValidationException {
        String exp = expression.toUpperCase();
        if (exp.equals(CODE_APP)) throw new ValidationException(String.format(ERROR, "Fatal error."));
        if (exp.isEmpty()) throw new ValidationException(MESSAGE_WARNING);

        boolean val1 = exp.equals("QUIT") || exp.equals("NEW") || exp.equals("REPORT");
        boolean val2 = exp.contains("TRUCK") || exp.contains("CAR");
        boolean val3 = exp.contains("ENTER") || exp.contains("EXIT");
        boolean val4 = exp.contains("TRUCK") && exp.contains("CAR");
        boolean val5 = exp.contains("ENTER") && exp.contains("EXIT");
        boolean val6 = val2 || val3;

        if (!val1) {
            if (!val2 || val4 || !val6) throw new ValidationException(String.format(ERROR, ERROR_SUBJECT));
            if (!val3 || val5) throw new ValidationException(String.format(ERROR, ERROR_ACT));
        }
    }

    public void validate(int bad_operation_code) throws ValidationException {
        if (bad_operation_code == 1)
            throw new ValidationException(String.format(ERROR, ERROR_NOT_AVAILABLE));
        if (bad_operation_code == 2)
            throw new ValidationException(String.format(ERROR, ERROR_NO_VEHICLE));
    }
}
