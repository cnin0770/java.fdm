package com.fdm.CarParkApp;

import org.junit.Test;

import static org.junit.Assert.*;

public class PropertiesTest {
    @Test
    public void main_test() {
        AppConstants constants = new AppConstants();
        assertEquals("[APP] ", constants.PROMPT);
    }
}
