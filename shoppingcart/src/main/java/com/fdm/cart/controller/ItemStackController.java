package com.fdm.cart.controller;

import com.fdm.cart.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemStackController {

    private final ItemStackRepo itemStackRepo;
    private final CartRepo cartRepo;
    private final ItemRepo itemRepo;

    @Autowired
    public ItemStackController(ItemStackRepo itemStackRepo, CartRepo cartRepo, ItemRepo itemRepo) {
        this.itemStackRepo = itemStackRepo;
        this.cartRepo = cartRepo;
        this.itemRepo = itemRepo;
    }

    @GetMapping("/api/stack/change")
    public void updateItemInCart(
            @RequestParam(value = "cartId") Long cartId,
            @RequestParam(value = "itemId") Long itemId,
            @RequestParam(value = "quantity") int quantity
    ) {
        Cart cart = cartRepo.findById(cartId).orElse(null);
        Item item = itemRepo.findById(itemId).orElse(null);

        if (cart == null || item == null) return;

        ItemStack stack = itemStackRepo.findItemStackByCartAndItem(cart, item);

        if (stack == null) {
            stack = ItemStack.builder()
                    .cart(cart)
                    .item(item)
                    .quantity(0)
                    .build();
        } else {
            cart.setSum(cart.getSum() - stack.stackSum());
        }

        stack.changeQuantity(quantity);
        cart.setSum(cart.getSum() + stack.stackSum());

        itemStackRepo.save(stack);
        itemRepo.save(item);
        cartRepo.save(cart);
    }

    @GetMapping("/api/stack/add")
    public void addItemToCart(
            @RequestParam(value = "cartId") Long cartId,
            @RequestParam(value = "itemId") Long itemId,
            @RequestParam(value = "quantity") int quantity
    ) {
        Cart cart = cartRepo.findById(cartId).orElse(null);
        Item item = itemRepo.findById(itemId).orElse(null);

        if (cart == null || item == null) return;

        ItemStack stack = itemStackRepo.findItemStackByCartAndItem(cart, item);

        if (stack == null) {
            stack = ItemStack.builder()
                    .cart(cart)
                    .item(item)
                    .quantity(0)
                    .build();
        } else {
            cart.setSum(cart.getSum() - stack.stackSum());
        }

        stack.changeQuantity(stack.getQuantity() + quantity);
        cart.setSum(cart.getSum() + stack.stackSum());

        itemStackRepo.save(stack);
        itemRepo.save(item);
        cartRepo.save(cart);
    }

    @GetMapping("/api/stacks/{cartId}")
    public List<ItemStack> getAllStacksByCartId(@PathVariable Long cartId) {
        Cart cart = cartRepo.findById(cartId).orElse(null);
        if (cart == null) return null;
        return itemStackRepo.findItemStacksByCart(cart);
    }

    @GetMapping("/api/stack/{id}")
    public ItemStack getItemStackById(@PathVariable Long id) {
        return itemStackRepo.findById(id).orElse(null);
    }

    @DeleteMapping("/api/stack/{id}")
    public void delItemById(@PathVariable Long id) {
        itemStackRepo.deleteById(id);
        itemStackRepo.flush();
    }

    @PostMapping("/api/stack")
    public long saveCart(@RequestBody ItemStack stack) {
        itemStackRepo.save(stack);
        itemStackRepo.flush();
        return stack.getId();
    }
}
