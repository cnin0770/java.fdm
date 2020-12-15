package com.fdm.cart.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;
    private double price;
    private int inventory;

    public int changeInventory(int quantity) {
        int remain = inventory;
        remain += quantity;
        if (remain < 0) return -1;
        inventory = remain;
        return inventory;
    }
}
