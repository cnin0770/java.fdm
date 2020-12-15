package com.fdm.CarParkApp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

class JsonIO {
    private static Logger logger = LogManager.getLogger(JsonIO.class.getName());
    static void serialize(Park park) {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(AppConstants.FILE_RECORD);
        try {
            mapper.writeValue(file, park);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    static Park deserialize() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(AppConstants.FILE_RECORD);
        Park park = new Park();
        try {
            park = mapper.readValue(file, park.getClass());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return park;
    }
}
