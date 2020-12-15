package com.fdm.pond.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Router {

    @GetMapping("/")
    public String getIndex() {
        return "";
    }
}
