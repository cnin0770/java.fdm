package com.fdm.BarrierOptionMonitor.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fdm.BarrierOptionMonitor.dal.AccountRepo;
import com.fdm.BarrierOptionMonitor.model.Account;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountControllerTest {

    AccountController controller;

    @Mock
    AccountRepo mockAccountRepo;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        controller = new AccountController(mockAccountRepo);
    }

    @Test
    public void that_context_loaded() {
        assertNotNull(controller);
    }

    @Test
    public void that_yahoo_fiance_can_give_result() throws IOException {
        Stock stock = YahooFinance.get("INTC");

        BigDecimal price = stock.getQuote(true).getPrice();

        assertNotNull(stock);
        assertNotEquals(0, price);
    }

    @Test
    public void that_multiple_stocks_can_be_fetched() throws IOException {
        String[] symbols = new String[]{"INTC", "BABA", "TSLA", "AIR.PA", "YHOO"};
        Map<String, Stock> stocks = YahooFinance.get(symbols);
        Stock intel = stocks.get("INTC");

        assertNotNull(intel);
        assertNotEquals(0, intel.getQuote().getPrice());
    }

    @Test
    public void test_thatGetAllAccounts_returnsListOfAccounts() {
        List<Account> foundAccounts = null;
        when(mockAccountRepo.findAll()).thenReturn(foundAccounts = new ArrayList<Account>());
        controller.getAccounts();
        verify(mockAccountRepo, times(1)).findAll();
        assertNotNull(foundAccounts);
    }

    @Test
    public void test_getAccountsByClientReturns_ListOfAccounts() {
        Long id = 1L;
        List<Account> foundAccounts = null;
        when(mockAccountRepo.CustomFindByClientID(id)).thenReturn(foundAccounts = new ArrayList<Account>());
        controller.getAccountsByClient(id);
        verify(mockAccountRepo, times(1)).CustomFindByClientID(id);
        assertNotNull(foundAccounts);
    }

    @Test
    public void test_saveAccount_savesAnAccount() {
        Account account = new Account();
        controller.save(account);
        verify(mockAccountRepo, times(1)).save(account);
        verify(mockAccountRepo, times(1)).flush();
    }

    @Test
    public void test_canRemoveAccount_byID() {
        Long id = 1L;
        controller.get(id);
        verify(mockAccountRepo, times(1)).deleteById(id);
    }
}
