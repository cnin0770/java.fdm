package com.fdm.w8.inheritence;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ExtendToOne_type", discriminatorType = DiscriminatorType.STRING)
public class ExtendToOne {
    @Id
    private int inheritedId;

    public ExtendToOne() {}
    public ExtendToOne(int inheritedId) {
        this.inheritedId = inheritedId;
    }

    public int getInheritedId() {
        return inheritedId;
    }

    public void setInheritedId(int inheritedId) {
        this.inheritedId = inheritedId;
    }
}
