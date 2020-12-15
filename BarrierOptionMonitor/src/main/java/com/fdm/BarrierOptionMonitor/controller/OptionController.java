package com.fdm.BarrierOptionMonitor.controller;

import com.fdm.BarrierOptionMonitor.dal.AccountRepo;
import com.fdm.BarrierOptionMonitor.model.Account;
import com.fdm.BarrierOptionMonitor.model.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import com.fdm.BarrierOptionMonitor.dal.NotificationRepo;
import com.fdm.BarrierOptionMonitor.dal.OptionRepo;
import com.fdm.BarrierOptionMonitor.model.Notifications;
import com.fdm.BarrierOptionMonitor.model.Option;
import com.fdm.BarrierOptionMonitor.model.OptionStatus;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;

import java.io.IOException;
import java.security.Principal;
import java.util.*;

@Log4j2
@RestController
public class OptionController {

    private OptionRepo optionRepo;
    private AccountRepo accountRepo;
    private NotificationRepo notificationRepo;

    @Autowired
    public OptionController(OptionRepo optionRepo, AccountRepo accountRepo, NotificationRepo notificationRepo) {
        this.optionRepo = optionRepo;

        this.accountRepo = accountRepo;
        this.notificationRepo = notificationRepo;
    }

    @Scheduled(fixedDelay = 5000)
    private void updateAllOptions() {
        List<Option> options = optionRepo.findAll();

        Iterator<Option> iterator = options.iterator();
        while (iterator.hasNext()) {
            Option option = iterator.next();
            OptionStatus status = option.update(true);
            optionRepo.save(option);
            optionRepo.flush();

            List<Notifications> foundNotifications = notificationRepo.findAll();
            for (Notifications not : foundNotifications) {
                not.update(status);
                notificationRepo.save(not);
            }
        }
    }

    @GetMapping("/api/notifications")
    public Notifications getNotifications(Principal principal) {
        Notifications notification = notificationRepo.CustomFindNotificationByName(principal.getName());
        return notification;
    }

    @PostMapping("/api/notifications")
    public Notifications removeNotifications(Principal principal) {
        Notifications notification = notificationRepo.CustomFindNotificationByName(principal.getName());
        notification.clearAll();
        notificationRepo.save(notification);
        return notification;
    }

    @GetMapping("/api/options")
    public List<Option> getAll() {
        updateAllOptions();
        return optionRepo.findAll();
    }

    @GetMapping("/api/check_price/{symbol}")
    public String checkPriceNotSaving(@PathVariable String symbol) {
        String result = "";
        try {
            Stock stock = YahooFinance.get(symbol);
            result = String.join(" ", stock.getCurrency(), stock.getQuote().getPrice().toString());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return result;
    }

    private Account prepareAccount(Client client, String currency) {
        Account account = accountRepo.getAccountByClientAndCurrency(client, currency);
        if (account == null) {
            account = new Account();
            account.setClient(client);
            account.setCurrency(currency);
            account.setBalance(0);
        }

        return account;
    }

    @PostMapping("/api/options")
    public Option saveAndUpdate(@RequestBody Option option) {
        option.update(false);
        Client client = option.getClient();

        Account account = prepareAccount(client, option.getCurrency());
        account.lose(option.getPremium());

        accountRepo.save(account);
        Option saved = optionRepo.save(option);

        accountRepo.flush();
        optionRepo.flush();
        return saved;
    }

    @GetMapping("/api/options/{id}")
    public Option getOption(@PathVariable Long id) {
        Option option = optionRepo.findById(id).orElse(null);
        if (option == null) {
            log.error("Option #{} not found.", id);
        }
        return option;
    }

    @DeleteMapping("/api/options/{id}")
    public void get(@PathVariable Long id) {
        Option option = optionRepo.findById(id).orElse(null);
        if (option == null) {
            log.error("Option #{} not found.", id);
            return;
        }
        optionRepo.deleteById(id);
    }

    @GetMapping("/api/getStocks")
    public Set<CustomStock> getStocksBack() {
        List<Option> options = optionRepo.findAll();
        if (options.isEmpty())
            return null;

        Set<CustomStock> results = new TreeSet<>(new StockComparator());
        for (Option option : options) {
            CustomStock stock = new CustomStock();
            stock.setSymbol(option.getStockSymbol());
            stock.setName(option.getStockName());
            stock.setCurrency(option.getCurrency());
            stock.setPrice(option.getCurrentPrice());

            results.add(stock);
        }
        return results;
    }

    @GetMapping("/api/chart/{symbol}")
    public List<HistoricalQuote> getHistory(@PathVariable String symbol) {
        List<HistoricalQuote> result = null;
        try {
            Stock stock = YahooFinance.get(symbol);
            result = stock.getHistory();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return result;
    }

    @GetMapping("/api/options/exercises/{id}")
    public void exerciseOption(@PathVariable long id) {
        Option option = optionRepo.findById(id).orElse(null);
        if (option == null) return;
        Account account = prepareAccount(option.getClient(), option.getCurrency());
        option.exercise(account);
        optionRepo.save(option);
        accountRepo.save(account);
        optionRepo.flush();
        accountRepo.flush();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private class CustomStock {
        private String symbol;
        private String name;
        private String currency;
        private double price;
    }

    private class StockComparator implements Comparator<CustomStock> {
        @Override
        public int compare(CustomStock o1, CustomStock o2) {
            return o1.symbol.compareTo(o2.symbol);
        }
    }
}
