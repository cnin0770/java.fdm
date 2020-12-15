package com.fdm.BarrierOptionMonitor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {
    public String index() {
        return "index";
    }

    @GetMapping("/")
    public String securedIndex(Principal principal, Model model) {
        if (principal != null) model.addAttribute("username", principal.getName());
        else model.addAttribute("username", "");
        return "index";
    }

    @GetMapping("/addOptions")
    public String getAddOptions() {
        return "addOptions";
    }

    @GetMapping("/viewOptions")
    public String getViewOptions() {
        return "viewOptions";
    }

    @GetMapping("/viewClients")
    public String getViewClients() {
        return "viewClients";
    }

    @GetMapping("/viewStocks")
    public String viewStocks() {
        return "viewStocks";
    }
}
