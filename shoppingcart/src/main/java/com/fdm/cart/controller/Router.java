package com.fdm.cart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Router {

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/cart/list")
    public String getCarts() {
        return "cart/list";
    }

    @GetMapping("/cart/new")
    public String newCart() {
        return "cart/new";
    }

    @GetMapping("/item/list")
    public String getItems() {
        return "item/list";
    }

    @GetMapping("/item/new")
    public String newItem() {
        return "item/new";
    }
}
