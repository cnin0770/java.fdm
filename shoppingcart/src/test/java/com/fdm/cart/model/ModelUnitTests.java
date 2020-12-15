package com.fdm.cart.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Slf4j
@SpringBootTest
public class ModelUnitTests {

    private CartRepo cartRepo;
    private ItemRepo itemRepo;
    private ItemStackRepo itemStackRepo;

    @Autowired
    @Before
    public void init(CartRepo cartRepo,
                     ItemRepo itemRepo,
                     ItemStackRepo itemStackRepo) {
        this.cartRepo = cartRepo;
        this.itemRepo = itemRepo;
        this.itemStackRepo = itemStackRepo;
    }

    @Test
    public void that_loads_items_from_db() {
        assertTrue(itemRepo.findAll().size() > 1);
    }

    @Test
    public void that_item_can_be_added_to_cart_at_quantity_predefined() {
        Cart cart = new Cart();
        cartRepo.save(cart);
        Item item = Item.builder().name("shoe").description("one left shoe").price(70).inventory(100).build();
        itemRepo.save(item);

        ItemStack itemStack = itemStackRepo.findItemStackByCartAndItem(cart, item);
        if (itemStack == null) itemStack = ItemStack.builder().cart(cart).item(item).build();

        itemStack.changeQuantity(10);

        assertEquals(10, itemStack.getQuantity());
        assertEquals("shoe", itemStack.getItem().getName());
    }

    @Test
    public void that_item_can_be_removed_to_cart_at_quantity_predefined() {
        Cart cart = new Cart();
        cartRepo.save(cart);
        Item item = Item.builder().name("shoe").description("one left shoe").price(70).inventory(100).build();
        itemRepo.save(item);

        ItemStack itemStack = itemStackRepo.findItemStackByCartAndItem(cart, item);
        if (itemStack == null) itemStack = ItemStack.builder().cart(cart).item(item).build();

        itemStack.changeQuantity(10);

        itemStack.changeQuantity(5);

        assertEquals(5, itemStack.getQuantity());
    }

    @Test
    public void that_change_in_cart_can_update_item_inventory() {
        Cart cart = new Cart();
        cartRepo.save(cart);
        Item item = Item.builder().name("shoe").description("one left shoe").price(70).inventory(100).build();
        itemRepo.save(item);

        ItemStack itemStack = itemStackRepo.findItemStackByCartAndItem(cart, item);
        if (itemStack == null) itemStack = ItemStack.builder().cart(cart).item(item).build();

        itemStack.changeQuantity(10);

        assertEquals(100 - 10, itemStack.getItem().getInventory());

        itemStack.changeQuantity(3);

        assertEquals(100 - 10 + (10 - 3), itemStack.getItem().getInventory());
    }
}
