package com.fdm.CarParkApp;

public interface IntView {
    String getInput();
    void displayWelcome();
    void displayReopen(int parking_slot);
    void closeScanner();
    void displayInfo(String expression);
    void report(String expression);
    void displayError(String expression);
}
