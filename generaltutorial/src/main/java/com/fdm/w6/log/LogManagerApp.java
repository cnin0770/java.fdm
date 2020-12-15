package com.fdm.w6.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogManagerApp {
//    private static Logger rootLogger = LogManager.getRootLogger();
//    private static Logger heyLogger = LogManager.getLogger("Hey");
    private static Logger loggerByClassString = LogManager.getLogger("com.fdm.w6.log.LogManagerApp");
//    private static Logger loggerByClassName = LogManager.getLogger(LogManagerApp.class.getName());
//    private static Logger loggerByClassObject = LogManager.getLogger(LogManager.class);

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("??)!jfkd");
    }
    private class Wula {
        int c = 2;
        Wula() {}
    }
}
