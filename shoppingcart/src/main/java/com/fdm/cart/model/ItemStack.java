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
public class ItemStack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int quantity;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Cart cart;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Item item;

    public boolean changeQuantity(int changedQuantity) {
        if (quantity == changedQuantity) return true;

        int change = quantity - changedQuantity;
        if (item.changeInventory(change) < 0) return false;

        cart.setTotalQuantity(cart.getTotalQuantity() - change);
        quantity = changedQuantity;
        return true;
    }

    public double stackSum() {
        if (item == null) return 0;
        return quantity * item.getPrice();
    }
}
