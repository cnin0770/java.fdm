package com.fdm.CarParkApp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

class AppConstants {
    private static final Logger logger = LogManager.getLogger(AppConstants.class.getName());

    static final double CAR_RATE = Double.parseDouble(getValue("car_rate"));
    static final double TRUCK_RATE = Double.parseDouble(getValue("truck_rate"));
    static final int CAR_TRUCK_RATE = Integer.parseInt(getValue("car_truck_rate"));
    static final int TOTAL_SPACE = Integer.parseInt(getValue("total_space"));

    static final String CODE_APP = UUID.randomUUID().toString();
    static final String CODE_EXIT = getValue("code_exit");
    static final String CODE_NEW = getValue("code_new");
    static final String PROMPT = getValue("prompt");

    static final String MESSAGE_WELCOME = PROMPT + String.format(getValue("message_welcome"), TOTAL_SPACE);
    static final String MESSAGE_REOPEN = PROMPT + getValue("message_reopen");
    static final String MESSAGE_BYE = PROMPT + getValue("message_bye");
    static final String MESSAGE_WARNING = PROMPT + String.format(getValue("message_warning"), CODE_EXIT, CODE_NEW);
    static final String MESSAGE_PRICING_TC = PROMPT + getValue("message_pricing_tc");
    static final String MESSAGE_PRICING = PROMPT + String.format(getValue("message_pricing"), CAR_RATE, TRUCK_RATE);
    static final String MESSAGE_REPORT = getValue("message_report");
    static final String MESSAGE_NEW = getValue("message_new");

    static final String ERROR = getValue("error");
    static final String ERROR_SUBJECT = getValue("error_subject");
    static final String ERROR_ACT = getValue("error_act");
    static final String ERROR_NOT_AVAILABLE = getValue("error_not_available");
    static final String ERROR_NO_VEHICLE = getValue("error_no_vehicle");

    static final String FILE_RECORD = getValue("file_record");
    static final String PROPERTIES_FILE = "CarParkApp.properties";

    static String getValue(String key) {
        InputStream inputStream = null;
        Properties prop = new Properties();

        try {
            inputStream = AppConstants.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE);

            if (inputStream != null) prop.load(inputStream);
            else throw new FileNotFoundException(String.format("Property file %s not found.", PROPERTIES_FILE));
            inputStream.close();
            return prop.getProperty(key.toUpperCase());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
