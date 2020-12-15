package com.fdm.w6.threading.customkey;

class Matt extends aBreakfastPeople {
    Matt (Cereal cereal, Milk milk, Object custom_key) {
        super(cereal, milk, custom_key);
    }

    @Override
    void prepareBf(Milk milk, Cereal cereal) {
        milk.execute(this.getClass().getSimpleName());
        cereal.execute(this.getClass().getSimpleName());
    }
}
