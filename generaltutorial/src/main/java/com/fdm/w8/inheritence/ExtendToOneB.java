package com.fdm.w8.inheritence;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "ExtendToOneB")
public class ExtendToOneB extends ExtendToOne {
    private String heirTwo;

    public ExtendToOneB() {
        super();
    }

    public ExtendToOneB(int id, String str) {
        super(id);
        this.heirTwo = str;
    }

    public String getStr() {
        return heirTwo;
    }

    public void setStr(String str) {
        this.heirTwo = str;
    }
}
