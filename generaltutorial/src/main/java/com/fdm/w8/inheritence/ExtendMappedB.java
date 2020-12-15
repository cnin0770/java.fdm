package com.fdm.w8.inheritence;

import javax.persistence.Entity;

@Entity
public class ExtendMappedB extends ExtendMapped {
    private String lowerStr;

    public ExtendMappedB() {
        super();
    }

    public ExtendMappedB(int upperId, String str, String lowerStr) {
        super(upperId, str);
        this.lowerStr = lowerStr;
    }

    public String getLowerStr() {
        return lowerStr;
    }

    public void setLowerStr(String lowerStr) {
        this.lowerStr = lowerStr;
    }
}
