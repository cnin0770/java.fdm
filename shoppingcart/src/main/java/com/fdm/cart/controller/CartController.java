package com.fdm.cart.controller;

import com.fdm.cart.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {

    private final CartRepo cartRepo;

    @Autowired
    public CartController(CartRepo cartRepo) {
        this.cartRepo = cartRepo;
    }

    @GetMapping("/api/carts")
    public List<Cart> getAllCarts() {
        return cartRepo.findAll();
    }

    @GetMapping("/api/cart/{id}")
    public Cart getCartById(@PathVariable Long id) {
        return cartRepo.findById(id).orElse(null);
    }

    @DeleteMapping("/api/cart/{id}")
    public void delCartById(@PathVariable Long id) {
        cartRepo.deleteById(id);
        cartRepo.flush();
    }

    @PostMapping("/api/cart")
    public long saveCart(@RequestBody Cart cart) {
        cartRepo.save(cart);
        cartRepo.flush();
        return cart.getId();
    }
}
