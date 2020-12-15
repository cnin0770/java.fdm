package com.fdm.w8.inheritence;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ExtendToMany {
    @Id
    private int extendToManyId;

    public ExtendToMany() {}

    public ExtendToMany(int id) {
        this.extendToManyId = id;
    }

    public int getExtendToManyId() {
        return extendToManyId;
    }

    public void setExtendToManyId(int extendToManyId) {
        this.extendToManyId = extendToManyId;
    }
}
