package com.fdm.w8.inheritence;

import javax.persistence.Entity;

@Entity
public class ExtendToManyB extends ExtendToMany {
    private double extendToManyBDouble;

    public ExtendToManyB() {
        super();
    }

    public ExtendToManyB(int id, double extendToManyBDouble) {
        super(id);
        this.extendToManyBDouble = extendToManyBDouble;
    }

    public double getExtendToManyBDouble() {
        return extendToManyBDouble;
    }

    public void setExtendToManyBDouble(double extendToManyBDouble) {
        this.extendToManyBDouble = extendToManyBDouble;
    }
}
