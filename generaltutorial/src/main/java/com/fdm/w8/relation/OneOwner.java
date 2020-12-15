package com.fdm.w8.relation;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class OneOwner {
    @Id
    private int oneOwnerId;
    private String str;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "oneOwneeId")
    private OneOwnee oneOwnee;
    @OneToMany(mappedBy = "oneOwner")
    private List<ManyOwnee> manyOwneeList = new ArrayList<>();

    public OneOwner() {}

    public OneOwner(int id, String str) {
        this.oneOwnerId = id;
        this.str = str;
    }

    public int getOneOwnerId() {
        return oneOwnerId;
    }

    public void setOneOwnerId(int id) {
        this.oneOwnerId = id;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public OneOwnee getOneOwnee() {
        return oneOwnee;
    }

    public void setOneOwnee(OneOwnee oneOwnee) {
        if (this.oneOwnee == oneOwnee) return;
        this.oneOwnee = oneOwnee;
        oneOwnee.setOneOwner(this);
    }

    public void add(ManyOwnee manyOwnee) {
        if (manyOwneeList.contains(manyOwnee)) return;
        manyOwneeList.add(manyOwnee);
        manyOwnee.setOneOwner(this);
    }
}
