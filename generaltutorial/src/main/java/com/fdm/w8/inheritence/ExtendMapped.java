package com.fdm.w8.inheritence;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class ExtendMapped {
    @Id
    private int upperId;
    private String str;

    public ExtendMapped() {
    }

    public ExtendMapped(int upperId, String str) {
        this.upperId = upperId;
        this.str = str;
    }

    public int getUpperId() {
        return upperId;
    }

    public void setUpperId(int upperId) {
        this.upperId = upperId;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
