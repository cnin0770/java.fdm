package com.fdm.w8.inheritence;

import javax.persistence.Entity;

@Entity
public class ExtendMappedA extends ExtendMapped {
    private int lowerId;

    public ExtendMappedA() {
        super();
    }

    public ExtendMappedA(int upperId, String str, int lowerId) {
        super(upperId, str);
        this.lowerId = lowerId;
    }

    public int getLowerId() {
        return lowerId;
    }

    public void setLowerId(int lowerId) {
        this.lowerId = lowerId;
    }
}
