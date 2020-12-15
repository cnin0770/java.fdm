package com.fdm.CarParkApp;

public class CarParkApp {
    public static void main(String[] args) {
        IntValidator validator = new Validator();
        IntView view = new View();
        Controller controller = new Controller(validator, view);
        controller.handle();
    }
}
