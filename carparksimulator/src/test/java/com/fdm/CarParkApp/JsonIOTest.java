package com.fdm.CarParkApp;

import org.junit.Test;

import static org.junit.Assert.*;

public class JsonIOTest {
    @Test
    public void main_test() {
        Park park = new Park();
        JsonIO.serialize(park);
        Park park2 = JsonIO.deserialize();
        assertTrue(park.equals(park2));
    }
}
