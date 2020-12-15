package com.fdm.w8.inheritence;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class ExtendJoinSub {
    @Id
    private int extendJoinSubId;

    public ExtendJoinSub() {}

    public ExtendJoinSub(int id) {
        this.extendJoinSubId = id;
    }

    public int getExtendJoinSubId() {
        return extendJoinSubId;
    }

    public void setExtendJoinSubId(int extendJoinSubId) {
        this.extendJoinSubId = extendJoinSubId;
    }
}
