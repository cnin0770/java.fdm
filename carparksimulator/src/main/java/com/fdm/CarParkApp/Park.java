package com.fdm.CarParkApp;

import java.util.Objects;

import static com.fdm.CarParkApp.AppConstants.*;

class Park {
    private int parking_slot = TOTAL_SPACE;
    private int car_in = 0;
    private int truck_in = 0;
    private int car_out = 0;
    private int truck_out = 0;
    private double fee_paid = 0;

    public Park() {}

    public int getParking_slot() { return parking_slot; }
    public void setParking_slot(int a) { parking_slot = a; }

    public int getCar_in() { return car_in; }
    public void setCar_in(int a) { car_in = a; }

    public int getTruck_in() { return truck_in; }
    public void setTruck_in(int a) { truck_in = a; }

    public int getCar_out() { return car_out; }
    public void setCar_out(int a) { car_out = a; }

    public int getTruck_out() { return truck_out; }
    public void setTruck_out(int a) { truck_out = a; }

    public double getFee_paid() { return fee_paid; }
    public void setFee_paid(double a) { fee_paid = a; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Park park = (Park) o;
        return parking_slot == park.parking_slot &&
                car_in == park.car_in &&
                truck_in == park.truck_in &&
                car_out == park.car_out &&
                truck_out == park.truck_out &&
                Double.compare(park.fee_paid, fee_paid) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(parking_slot, car_in, truck_in, car_out, truck_out, fee_paid);
    }

    @Override
    public String toString() {
        return String.format(MESSAGE_REPORT, car_in, truck_in, car_out, truck_out, car_in - car_out, truck_in - truck_out, parking_slot, fee_paid);
    }

    boolean enterCar() {
        if (parking_slot <= 0)
            return false;
        car_in += 1;
        parking_slot -= 1;
        return true;
    }

    boolean enterTruck() {
        if (parking_slot / CAR_TRUCK_RATE <= 0)
            return false;
        truck_in += 1;
        parking_slot -= CAR_TRUCK_RATE;
        return true;
    }

    boolean exitCar(int hour) {
        if (car_in <= car_out)
            return false;
        car_out += 1;
        parking_slot += 1;
        fee_paid += CAR_RATE * hour;
        return true;
    }

    boolean exitTruck(int hour) {
        if (truck_in <= truck_out)
            return false;
        truck_out += 1;
        parking_slot += CAR_TRUCK_RATE;
        fee_paid += TRUCK_RATE * hour;
        return true;
    }
}
