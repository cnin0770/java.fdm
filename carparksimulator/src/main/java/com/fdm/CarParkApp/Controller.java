package com.fdm.CarParkApp;

import static com.fdm.CarParkApp.AppConstants.*;

class Controller implements IntController {
    private IntValidator validator;
    private IntView view;
    private Park park;

    Controller(IntValidator validator, IntView view) {
        this.validator = validator;
        this.view = view;
        this.park = new Park();
    }

    private void legacyHandler() {
        Park legacy = JsonIO.deserialize();
        if (legacy.equals(park))
            view.displayWelcome();
        else {
            park = legacy;
            view.displayReopen(park.getParking_slot());
        }
    }

    private int hourHandler(String expression) {
        String value = expression.replaceAll("[^0-9]", "");
        return (value.isEmpty()) ? 1: Integer.parseInt(value);
    }

    private void commander(String expression) {
        boolean legal_command = false;
        int hours;
        if (expression.equals(CODE_EXIT)) {
            JsonIO.serialize(park);
            view.closeScanner();
            view.displayInfo(MESSAGE_BYE);
            System.exit(0);
        } else if (expression.equals(CODE_NEW)) {
            park = new Park();
            view.displayInfo(MESSAGE_NEW);
            view.displayWelcome();
        } else if (expression.equals("REPORT")) {
            view.report(park.toString());
        } else {
            try {
                if (expression.contains("ENTER")) {
                    if (expression.contains("CAR")) legal_command = park.enterCar();
                    else if (expression.contains("TRUCK")) legal_command = park.enterTruck();
                    validator.validate((legal_command) ? 0 : 1);
                } else if (expression.contains("EXIT")) {
                    hours = hourHandler(expression);
                    if (expression.contains("CAR")) legal_command = park.exitCar(hours);
                    else if (expression.contains("TRUCK")) legal_command = park.exitTruck(hours);
                    validator.validate((legal_command) ? 0 : 2);
                }
            } catch (ValidationException e) {
                view.displayError(e.getMessage());
            }
        }
    }

    public void handle() {
        legacyHandler();
        while (true) {
            String expression = view.getInput().trim().toUpperCase();
            if (expression.equals(CODE_APP)) break;
            try {
                validator.validate(expression);
                commander(expression);
            } catch (ValidationException e) {
                view.displayError(e.getMessage());
            }
        }
    }
}
