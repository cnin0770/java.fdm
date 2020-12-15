package com.fdm.CarParkApp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class LogTest {
    private static final Logger logger = LogManager.getLogger(LogTest.class.getName());

    @Test
    public void main_test(){
        logger.trace("trace..");
        logger.info("info.");
        logger.error("boom!");
        logger.fatal("fatal!!");
    }
}
