package com.fdm.cart.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemStackRepo extends JpaRepository<ItemStack, Long> {
    List<ItemStack> findItemStacksByCart(Cart cart);
    ItemStack findItemStackByCartAndItem(Cart cart, Item item);
}
