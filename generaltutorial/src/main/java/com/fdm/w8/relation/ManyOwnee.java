package com.fdm.w8.relation;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ManyOwnee {
    @Id
    private int manyOwneeId;
    private String str;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "oneOwnerId")
    private OneOwner oneOwner;
    @ManyToMany(mappedBy = "manyOwneeList")
    private List<ManyOwner> manyOwnerList = new ArrayList<>();

    public ManyOwnee() {}

    public ManyOwnee(int id, String str) {
        this.manyOwneeId = id;
        this.str = str;
    }

    public int getManyOwneeId() {
        return manyOwneeId;
    }

    public String getStr() {
        return str;
    }

    public OneOwner getOneOwner() {
        return oneOwner;
    }

    public List<ManyOwner> getList() {
        return manyOwnerList;
    }

    public void setManyOwneeId(int id) {
        this.manyOwneeId = id;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public void setOneOwner(OneOwner oneOwner) {
        if (this.oneOwner == oneOwner) return;
        this.oneOwner = oneOwner;
        oneOwner.add(this);
    }

    public void add(ManyOwner manyOwner) {
        if (manyOwnerList.contains(manyOwner)) return;
        manyOwnerList.add(manyOwner);
        manyOwner.add(this);
    }
}
