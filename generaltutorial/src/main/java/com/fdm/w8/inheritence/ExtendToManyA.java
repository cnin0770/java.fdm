package com.fdm.w8.inheritence;

import javax.persistence.Entity;

@Entity
public class ExtendToManyA extends ExtendToMany {
    private double extendToManyADouble;

    public ExtendToManyA() {
        super();
    }

    public ExtendToManyA(int id, double extendToManyADouble) {
        super(id);
        this.extendToManyADouble = extendToManyADouble;
    }

    public double getExtendToManyADouble() {
        return extendToManyADouble;
    }

    public void setExtendToManyADouble(double extendToManyADouble) {
        this.extendToManyADouble = extendToManyADouble;
    }
}
