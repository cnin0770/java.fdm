package com.fdm.w6.threading.customkey;

public class BreakfastApp {
    public static void main(String[] args) {
        Milk milk = new Milk();
        Cereal cereal = new Cereal();
        Object custom_key = new Object();

        Thread matt_thread = new Thread(new Matt(cereal, milk, custom_key));
        Thread luke_thread = new Thread(new Luke(cereal, milk, custom_key));

        matt_thread.start();
        luke_thread.start();
    }
}
