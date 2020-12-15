package com.fdm.CarParkApp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ParkTest {
    Park park;

    @Before
    public void setPark() {
        park = new Park();
    }

    @Test
    public void returns_0_from_getFee_in_new_instance() {
        assertEquals(0, park.getFee_paid(), .01);
    }

    @Test
    public void returns_false_when_exit_car_in_new_instance() {
        assertFalse(park.exitCar(1));
        assertFalse(park.exitTruck(1));
    }

    @Test
    public void returns_true_when_enter_car_in_new_instance() {
        assertTrue(park.enterCar());
        assertTrue(park.enterTruck());
    }

    @Test
    public void returns_true_when_enter_car_then_exit_in_new_instance() {
        assertTrue(park.enterCar());
        assertTrue(park.exitCar(2));
    }

    @Test
    public void returns_false_when_enter_car_then_exit_truck_in_new_instance() {
        assertTrue(park.enterCar());
        assertFalse(park.exitTruck(2));
    }

    @Test
    public void returns_4_from_getFee_when_enter_then_exit_car_with_2_hours() {
        assertTrue(park.enterCar());
        assertTrue(park.exitCar(2));
        assertEquals(4.0, park.getFee_paid(), .01);
    }

    @Test
    public void return_13_from_slot_when_entered_truck() {
        assertTrue(park.enterTruck());
        assertEquals(13, park.getParking_slot());
    }

    @Test
    public void return_false_when_enter_in_full_park() {
        for (int i = 0; i < 7; ++i)
            assertTrue(park.enterTruck());
        assertFalse(park.enterTruck());
    }

    @Test
    public void return_true_when_enter_car_in_1_space_park() {
        for (int i = 0; i < 7; ++i)
            assertTrue(park.enterTruck());
        assertFalse(park.enterTruck());
        assertTrue(park.enterCar());
        assertEquals(0, park.getParking_slot());
    }
}
