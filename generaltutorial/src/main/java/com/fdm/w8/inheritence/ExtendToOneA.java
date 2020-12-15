package com.fdm.w8.inheritence;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "ExtendToOneA")
public class ExtendToOneA extends ExtendToOne {
    private String heirOne;

    public ExtendToOneA() {
        super();
    }

    public ExtendToOneA(int id, String str) {
        super(id);
        this.heirOne = str;
    }

    public String getStr() {
        return heirOne;
    }

    public void setStr(String str) {
        this.heirOne = str;
    }
}
