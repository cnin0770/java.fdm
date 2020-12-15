package com.fdm.w8.inheritence;

import javax.persistence.Entity;

@Entity
public class ExtendJoinSubA extends ExtendJoinSub {
    private String extendJoinSubAStr;

    public ExtendJoinSubA() {}

    public ExtendJoinSubA(int id, String str) {
        super(id);
        this.extendJoinSubAStr = str;
    }

    public String getExtendJoinSubAStr() {
        return extendJoinSubAStr;
    }

    public void setExtendJoinSubAStr(String extendJoinSubAStr) {
        this.extendJoinSubAStr = extendJoinSubAStr;
    }
}
