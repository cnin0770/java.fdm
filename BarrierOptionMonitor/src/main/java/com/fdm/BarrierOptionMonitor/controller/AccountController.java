package com.fdm.BarrierOptionMonitor.controller;

import com.fdm.BarrierOptionMonitor.dal.*;
import com.fdm.BarrierOptionMonitor.model.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
public class AccountController {
    private AccountRepo accountRepo;

    @Autowired
    public AccountController(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @GetMapping("/api/accounts")
    public List<Account> getAccounts() {
        return accountRepo.findAll();
    }

    @GetMapping("/api/accounts/{id}")
    public List<Account> getAccountsByClient(@PathVariable Long id) {
        return accountRepo.CustomFindByClientID(id);
    }

    @PostMapping("/api/account")
    public Account save(@RequestBody Account account) {
        accountRepo.save(account);
        accountRepo.flush();
        log.info("Account #{} saved.", account.getId());
        return account;
    }

    @DeleteMapping("/api/account/{id}")
    public void get(@PathVariable Long id) {
        accountRepo.deleteById(id);
    }
}
