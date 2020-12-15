package com.fdm.cart.controller;

import com.fdm.cart.model.HostValue;
import com.fdm.cart.model.HostValueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HostValueController {

    private final HostValueRepo hostValueRepo;

    @Autowired
    public HostValueController(HostValueRepo hostValueRepo) {
        this.hostValueRepo = hostValueRepo;
    }

    @GetMapping("/api/ccid")
    public long getCurrentCartId() {
        HostValue hostValue = hostValueRepo.findById(1L).orElse(null);
        return (hostValue != null) ? hostValue.getCurrentCartId() : 1;
    }

    @GetMapping("/api/ccid/{id}")
    public long saveCurrentCartId(@PathVariable long id) {
        HostValue hostValue = hostValueRepo.findById(1L).orElse(null);
        if (hostValue != null) {
            hostValue.setCurrentCartId(id);
            hostValueRepo.save(hostValue);
        }
        return id;
    }
}
