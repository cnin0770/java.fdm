package com.fdm.w8.relation;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ManyOwner {
    @Id
    private int manyOwnerId;
    private String str;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "oneOwneeId")
    private OneOwnee oneOwnee;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "ManyToMany",
            joinColumns = {@JoinColumn(name = "fk_manyOwner")},
            inverseJoinColumns = {@JoinColumn(name = "fk_manyOwnee")}
    )
    private List<ManyOwnee> manyOwneeList = new ArrayList<>();

    public ManyOwner() {}

    public ManyOwner(int id, String str) {
        this.manyOwnerId = id;
        this.str = str;
    }

    public int getManyOwnerId() {
        return manyOwnerId;
    }

    public String getStr() {
        return str;
    }

    public OneOwnee getOneOwnee() {
        return oneOwnee;
    }

    public List<ManyOwnee> getList() {
        return manyOwneeList;
    }

    public void setManyOwnerId(int id) {
        this.manyOwnerId = id;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public void setOneOwnee(OneOwnee oneOwnee) {
        if (this.oneOwnee == oneOwnee) return;
        this.oneOwnee = oneOwnee;
        oneOwnee.add(this);
    }

    public void add(ManyOwnee manyOwnee) {
        if (manyOwneeList.contains(manyOwnee)) return;
        manyOwneeList.add(manyOwnee);
        manyOwnee.add(this);
    }
}
