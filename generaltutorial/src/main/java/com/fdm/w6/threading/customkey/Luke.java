package com.fdm.w6.threading.customkey;

class Luke extends aBreakfastPeople {
    Luke (Cereal cereal, Milk milk, Object custom_key) {
        super(cereal, milk, custom_key);
    }

    @Override
    void prepareBf(Milk milk, Cereal cereal) {
        cereal.execute(this.getClass().getSimpleName());
        milk.execute(this.getClass().getSimpleName());
    }
}
