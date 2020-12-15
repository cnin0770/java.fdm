package com.fdm.BarrierOptionMonitor.controller;

import com.fdm.BarrierOptionMonitor.dal.AccountRepo;
import com.fdm.BarrierOptionMonitor.model.Client;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import com.fdm.BarrierOptionMonitor.dal.OptionRepo;
import com.fdm.BarrierOptionMonitor.dal.ClientRepo;

import java.util.List;

@Log4j2
@RestController
public class ClientController {
    private ClientRepo clientRepo;
    private OptionRepo optionRepo;
    private AccountRepo accountRepo;

    @Autowired
    public ClientController(ClientRepo clientRepo, OptionRepo optionRepo, AccountRepo accountRepo) {
        this.clientRepo = clientRepo;
        this.optionRepo = optionRepo;
        this.accountRepo = accountRepo;
    }

    @GetMapping("/api/clients")
    public List<Client> getAllClients() {
        return clientRepo.findAll();
    }

    @PostMapping("/api/clients")
    public Client save(@RequestBody Client client) {
        return clientRepo.save(client);
    }

    @GetMapping("/api/clients/{id}")
    public Client getClients(@PathVariable Long id) {
        Client client = clientRepo.findById(id).orElse(null);
        if (client == null) {
            log.error("Client #{} not found.", id);
        }
        return client;
    }

    @DeleteMapping("/api/clients/{id}")
    public void get(@PathVariable Long id) {
        optionRepo.CustomRemoveOptionForClient(id);
        accountRepo.CustomRemoveAccountForClient(id);
        clientRepo.deleteById(id);
    }
}
