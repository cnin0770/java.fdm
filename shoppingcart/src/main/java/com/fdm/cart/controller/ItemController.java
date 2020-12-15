package com.fdm.cart.controller;

import com.fdm.cart.model.Item;
import com.fdm.cart.model.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    private final ItemRepo itemRepo;

    @Autowired
    public ItemController(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    @GetMapping("/api/items")
    public List<Item> getAllItems() {
        return itemRepo.findAll();
    }

    @GetMapping("/api/item/{id}")
    public Item getItemById(@PathVariable Long id) {
        return itemRepo.findById(id).orElse(null);
    }

    @DeleteMapping("/api/item/{id}")
    public void delItemById(@PathVariable Long id) {
        itemRepo.deleteById(id);
        itemRepo.flush();
    }

    @PostMapping("/api/item")
    public long saveCart(@RequestBody Item item) {
        itemRepo.save(item);
        itemRepo.flush();
        return item.getId();
    }
}
