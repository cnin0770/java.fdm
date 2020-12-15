package com.fdm.w6.threading.customkey;

abstract class aBreakfastPeople implements Runnable {
    Cereal cereal;
    Milk milk;
    Object key;

    aBreakfastPeople (Cereal cereal, Milk milk, Object custom_key) {
        this.cereal = cereal;
        this.milk = milk;
        this.key = custom_key;
    }

    void bfReady() {
        System.out.println(this.getClass().getSimpleName() + " breakfast ready.");
    }

    abstract void prepareBf(Milk milk, Cereal cereal);

    @Override
    public void run() {
        synchronized (key) {
            prepareBf(milk, cereal);
            bfReady();
        }
    }
}
