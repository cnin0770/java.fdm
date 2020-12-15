package com.fdm.w8.relation;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class OneOwnee {
    @Id
    private int oneOwneeId;
    private String str;
    @OneToOne(mappedBy = "oneOwnee")
    private OneOwner oneOwner;
    @OneToMany(mappedBy = "oneOwnee")
    private List<ManyOwner> manyOwnerList = new ArrayList<>();

    public OneOwnee() {}

    public OneOwnee(int id, String str) {
        this.oneOwneeId = id;
        this.str = str;
    }

    public int getOneOwneeId() {
        return oneOwneeId;
    }

    public void setOneOwneeId(int id) {
        this.oneOwneeId = id;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public OneOwner getOneOwner() {
        return oneOwner;
    }

    public void setOneOwner(OneOwner oneOwner) {
        if (this.oneOwner == oneOwner) return;
        this.oneOwner = oneOwner;
    }

    public void add(ManyOwner manyOwner) {
        if (manyOwnerList.contains(manyOwner)) return;
        manyOwnerList.add(manyOwner);
        manyOwner.setOneOwnee(this);
    }
}
