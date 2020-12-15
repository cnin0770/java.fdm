package com.fdm.w8.inheritence;

import javax.persistence.Entity;

@Entity
public class ExtendJoinSubB extends ExtendJoinSub {
    private String extendJoinSubBStr;

    public ExtendJoinSubB() {}

    public ExtendJoinSubB(int id, String str) {
        super(id);
        this.extendJoinSubBStr = str;
    }

    public String getExtendJoinSubBStr() {
        return extendJoinSubBStr;
    }

    public void setExtendJoinSubBStr(String extendJoinSubBStr) {
        this.extendJoinSubBStr = extendJoinSubBStr;
    }
}
