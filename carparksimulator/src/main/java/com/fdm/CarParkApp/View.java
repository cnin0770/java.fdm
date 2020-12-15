package com.fdm.CarParkApp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

import static com.fdm.CarParkApp.AppConstants.*;

class View implements IntView {
    private static Scanner scanner = new Scanner(System.in);
    private static final Logger logger = LogManager.getLogger(View.class.getName());

    View() {}

    void displayLine(String expression) {
        System.out.println(expression);
    }

    public void displayInfo(String expression) {
        logger.info(expression);
    }

    public void displayError(String expression) {
        logger.error(expression);
    }

    public void displayWelcome() {
        displayInfo(MESSAGE_WELCOME);
        displayLine(MESSAGE_PRICING);
        displayLine(MESSAGE_PRICING_TC);
    }

    public void displayReopen(int available_space) {
        displayInfo(String.format(MESSAGE_REOPEN, available_space));
    }

    public void closeScanner() {
        scanner.close();
    }

    public String getInput() {
        System.out.printf("> ");

        try {
            return scanner.nextLine();
        } catch (Exception e){
            e.toString();
        }
        return CODE_APP;
    }

    public void report(String expression) {
        displayLine(PROMPT);
        displayInfo(expression);
    }
}
