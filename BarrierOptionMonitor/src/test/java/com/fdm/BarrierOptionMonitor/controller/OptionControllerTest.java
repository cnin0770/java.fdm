package com.fdm.BarrierOptionMonitor.controller;

import com.fdm.BarrierOptionMonitor.dal.AccountRepo;
import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.fdm.BarrierOptionMonitor.dal.NotificationRepo;
import com.fdm.BarrierOptionMonitor.dal.OptionRepo;
import com.fdm.BarrierOptionMonitor.model.Account;
import com.fdm.BarrierOptionMonitor.model.Client;
import com.fdm.BarrierOptionMonitor.model.Notifications;
import com.fdm.BarrierOptionMonitor.model.Option;
import com.fdm.BarrierOptionMonitor.model.OptionStatus;

import static org.mockito.Mockito.when;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Log4j2
public class OptionControllerTest {

    @Mock
    OptionRepo mockOptionRepo;
    @Mock
    NotificationRepo mockNotificationRepo;
    @Mock
    AccountRepo mockAccountRepo;

    OptionController optionController;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        optionController = new OptionController(mockOptionRepo, mockAccountRepo, mockNotificationRepo);
    }

    @Test
    public void test_getAllOptions_returnsListOfOptions_UpdatesKnockedIN_UP() {
        List<Option> foundOptions = new ArrayList<Option>();
        Option StatusKnockedInIsKnockedIN = new Option(1, true, true, true, 2, 2, "2020-05-15", true, 200, 100, "GOOG", "Google", "USD", 1300, new Client(), null, OptionStatus.KNOCKED_IN);
        Option StatusKnockedInAndWaiting = new Option(1, true, true, false, 2, 2, "2020-05-15", true, 200, 100, "GOOG", "Google", "USD", 1300, new Client(), null, OptionStatus.KNOCKED_IN);
        Option exercised = new Option(1, true, true, true, 2, 2, "2020-05-15", true, 200, 100, "GOOG", "Google", "USD", 1300, new Client(), null, OptionStatus.EXERCISED);
        Option knockedInUP = new Option(1, true, true, true, 2, 2, "2020-05-15", true, 200, 100, "GOOG", "Google", "USD", 1300, new Client(), null, OptionStatus.WAITING);
        Option knockedInUPNoChange = new Option(1, true, true, true, 2, 2, "2020-05-15", true, 1560, 100, "GOOG", "Google", "USD", 1300, new Client(), null, OptionStatus.WAITING);
        Option expired = new Option(2, true, true, true, 2, 2, "2020-05-10", true, 200, 100, "GOOG", "Google", "USD", 1300, new Client(), null, OptionStatus.WAITING);
        Option euro = new Option(3, false, true, true, 2, 2, "2020-05-25", true, 200, 100, "GOOG", "Google", "USD", 1300, new Client(), null, OptionStatus.WAITING);
        Option euroToday = new Option(3, false, true, true, 2, 2, "2020-05-11", true, 200, 100, "GOOG", "Google", "USD", 1300, new Client(), null, OptionStatus.WAITING);
        Option knockedInDown = new Option(4, true, true, true, 2, 2, "2020-05-20", false, 1500, 100, "GOOG", "Google", "USD", 1300, new Client(), null, OptionStatus.WAITING);
        Option knockedInDownNoChange = new Option(4, true, true, true, 2, 2, "2020-05-20", false, 1200, 100, "GOOG", "Google", "USD", 1300, new Client(), null, OptionStatus.WAITING);
        Option knockedOutUp = new Option(5, true, true, false, 2, 2, "2020-05-20", true, 1200, 100, "GOOG", "Google", "USD", 1300, new Client(), null, OptionStatus.WAITING);
        Option knockedOutUpNoChange = new Option(5, true, true, false, 2, 2, "2020-05-20", true, 1400, 100, "GOOG", "Google", "USD", 1300, new Client(), null, OptionStatus.WAITING);
        Option knockedOutDown = new Option(1, true, true, false, 2, 2, "2020-05-20", false, 1500, 100, "GOOG", "Google", "USD", 1300, new Client(), null, OptionStatus.WAITING);
        Option knockedOutDownNoChange = new Option(1, true, true, false, 2, 2, "2020-05-20", false, 1200, 100, "GOOG", "Google", "USD", 1300, new Client(), null, OptionStatus.WAITING);
        Option fakeDate = new Option(1, true, true, false, 2, 2, "NOW", false, 1500, 100, "GOOG", "Google", "USD", 1300, new Client(), null, OptionStatus.WAITING);
        Option noSymbol = new Option(1, true, true, false, 2, 2, "NOW", false, 1500, 100, "", "Google", "USD", 1300, new Client(), null, OptionStatus.WAITING);
        foundOptions.add(knockedInUP);
        foundOptions.add(expired);
        foundOptions.add(euro);
        foundOptions.add(knockedInDown);
        foundOptions.add(knockedOutUp);
        foundOptions.add(knockedOutDown);
        foundOptions.add(fakeDate);
        foundOptions.add(noSymbol);
        foundOptions.add(knockedInUPNoChange);
        foundOptions.add(knockedInDownNoChange);
        foundOptions.add(knockedOutUpNoChange);
        foundOptions.add(knockedOutDownNoChange);
        foundOptions.add(StatusKnockedInIsKnockedIN);
        foundOptions.add(exercised);
        foundOptions.add(euroToday);
        foundOptions.add(StatusKnockedInAndWaiting);
        List<Notifications> foundNotifications = new ArrayList<Notifications>();
        foundNotifications.add(new Notifications());
        when(mockOptionRepo.findAll()).thenReturn(foundOptions);
        when(mockNotificationRepo.findAll()).thenReturn(foundNotifications);
        optionController.getAll();
        verify(mockOptionRepo, times(2)).findAll();
        verify(mockNotificationRepo, times(16)).findAll();
        assertNotNull(foundOptions);
    }

    @Test
    public void test_getNotifications() {
        Principal principal = new Principal() {

            @Override
            public String getName() {
                // TODO Auto-generated method stub
                return "tim";
            }
        };
        Notifications foundNotification = new Notifications();
        when(mockNotificationRepo.CustomFindNotificationByName(principal.getName())).thenReturn(foundNotification);
        optionController.getNotifications(principal);
        verify(mockNotificationRepo, times(1)).CustomFindNotificationByName(principal.getName());
    }

    @Test
    public void test_removeNotifications() {
        Principal principal = new Principal() {

            @Override
            public String getName() {
                // TODO Auto-generated method stub
                return "tim";
            }
        };
        Notifications foundNotification = new Notifications();
        when(mockNotificationRepo.CustomFindNotificationByName(principal.getName())).thenReturn(foundNotification);
        optionController.removeNotifications(principal);
        verify(mockNotificationRepo, times(1)).CustomFindNotificationByName(principal.getName());
        verify(mockNotificationRepo, times(1)).save(foundNotification);
    }

    @Test
    public void test_checkPrice() {
        String symbol = "GOOG";
        optionController.checkPriceNotSaving(symbol);
    }

    @Test
    public void test_checkPrice_fails_ifNoStockSymbol() {
        String symbol = "";
        optionController.checkPriceNotSaving(symbol);
    }

    @Test
    public void test_getAllOptions_returnsEmptyListOfOptions() {
        List<Option> foundOptions = new ArrayList<Option>();
        when(mockOptionRepo.findAll()).thenReturn(foundOptions);
        optionController.getAll();
        verify(mockOptionRepo, times(2)).findAll();
        assertNotNull(foundOptions);
        assertTrue(foundOptions.isEmpty());
    }

    @Test
    public void test_savingOption_savesOption() {
        Option passedOption = new Option(1, true, true, true, 2, 2, "Today", true, 200, 100, "GOOG", "Google", "USD", 1300, new Client(), "Now", OptionStatus.KNOCKED_IN);
        optionController.saveAndUpdate(passedOption);
        verify(mockOptionRepo, times(1)).save(passedOption);
    }

    @Test
    public void test_returnsOption_whenPassID() {
        Long id = 1L;
        Optional<Option> foundOption = Optional.ofNullable(new Option());
        when(mockOptionRepo.findById(id)).thenReturn(foundOption);
        optionController.getOption(id);
        verify(mockOptionRepo, times(1)).findById(id);
        assertNotNull(foundOption);
    }

    @Test
    public void test_returnsNull_whenPassID() {
        Long id = 1L;
        Optional<Option> foundOption = Optional.ofNullable(null);
        when(mockOptionRepo.findById(id)).thenReturn(foundOption);
        optionController.getOption(id);
        verify(mockOptionRepo, times(1)).findById(id);
        assertNotNull(foundOption);
    }

    @Test
    public void test_optionIsDeleted_byPassedID() {
        Long id = 1L;
        Optional<Option> option = Optional.ofNullable(new Option());
        when(mockOptionRepo.findById(id)).thenReturn(option);
        optionController.get(id);
        verify(mockOptionRepo, times(1)).findById(id);
        verify(mockOptionRepo, times(1)).deleteById(id);
    }

    @Test
    public void test_IfNoOptionFoundNotDeleted_byPassedID() {
        Long id = 1L;
        Optional<Option> option = Optional.ofNullable(null);
        when(mockOptionRepo.findById(id)).thenReturn(option);
        optionController.get(id);
        verify(mockOptionRepo, times(1)).findById(id);
    }

    @Test
    public void test_date() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String todayStr = df.format(new Date());
        Date todayDate = df.parse(todayStr);
        log.info(todayStr);
        assertEquals(df.format(new Date()), df.format(todayDate));
    }

    @Test
    public void test_getStocksBack_ifStocks() {
        List<Option> foundOptions = new ArrayList<Option>();
        foundOptions.add(new Option(1, true, true, true, 2, 2, "2020-05-15", true, 200, 100, "GOOG", "Google", "USD", 1300, new Client(), null, OptionStatus.WAITING));
        when(mockOptionRepo.findAll()).thenReturn(foundOptions);
        optionController.getStocksBack();
        verify(mockOptionRepo, times(1)).findAll();
    }

    @Test
    public void test_getStocksBack_noStocks() {
        List<Option> foundOptions = new ArrayList<Option>();
        when(mockOptionRepo.findAll()).thenReturn(foundOptions);
        optionController.getStocksBack();
        verify(mockOptionRepo, times(1)).findAll();
    }

    @Test
    public void test_getHistoryOfStock() {
        String symbol = "GOOG";
        optionController.getHistory(symbol);
    }

    @Test
    public void test_getHistoryOfStock_NoStockPassed() {
        String symbol = "";
        optionController.getHistory(symbol);
    }

    @Test
    public void test_exerciseOption_Waiting_Call() {
        Long id = 1L;
        Client client = new Client(1, "Tim");
        Account account = new Account(1, "USD", 200, client);
        Option foundOption = new Option(1, true, true, true, 2, 2, "2020-05-15", true, 200, 100, "GOOG", "Google", "USD", 1300, client, null, OptionStatus.WAITING);
        Optional<Option> option = Optional.ofNullable(foundOption);
        when(mockOptionRepo.findById(id)).thenReturn(option);
        when(mockAccountRepo.getAccountByClientAndCurrency(client, "USD")).thenReturn(account);
        optionController.exerciseOption(id);
        verify(mockOptionRepo, times(1)).findById(id);
        verify(mockOptionRepo, times(1)).save(foundOption);
        verify(mockAccountRepo, times(1)).save(account);
        verify(mockOptionRepo, times(1)).flush();
        verify(mockAccountRepo, times(1)).flush();
    }

    @Test
    public void test_exerciseOption_KnockedIn_Call() {
        Long id = 1L;
        Client client = new Client(1, "Tim");
        Account account = new Account(1, "USD", 200, client);
        Option foundOption = new Option(1, true, true, true, 2, 2, "2020-05-15", true, 200, 100, "GOOG", "Google", "USD", 1300, client, null, OptionStatus.KNOCKED_IN);
        Optional<Option> option = Optional.ofNullable(foundOption);
        when(mockOptionRepo.findById(id)).thenReturn(option);
        when(mockAccountRepo.getAccountByClientAndCurrency(client, "USD")).thenReturn(account);
        optionController.exerciseOption(id);
        verify(mockOptionRepo, times(1)).findById(id);
        verify(mockOptionRepo, times(1)).save(foundOption);
        verify(mockAccountRepo, times(1)).save(account);
        verify(mockOptionRepo, times(1)).flush();
        verify(mockAccountRepo, times(1)).flush();
    }

    @Test
    public void test_exerciseOption_KnockedIn_Put() {
        Long id = 1L;
        Client client = new Client(1, "Tim");
        Account account = new Account(1, "USD", 200, client);
        Option foundOption = new Option(1, true, false, true, 2, 2, "2020-05-15", true, 200, 100, "GOOG", "Google", "USD", 1300, client, null, OptionStatus.KNOCKED_IN);
        Optional<Option> option = Optional.ofNullable(foundOption);
        when(mockOptionRepo.findById(id)).thenReturn(option);
        when(mockAccountRepo.getAccountByClientAndCurrency(client, "USD")).thenReturn(account);
        optionController.exerciseOption(id);
        verify(mockOptionRepo, times(1)).findById(id);
        verify(mockOptionRepo, times(1)).save(foundOption);
        verify(mockAccountRepo, times(1)).save(account);
        verify(mockOptionRepo, times(1)).flush();
        verify(mockAccountRepo, times(1)).flush();
    }

    @Test
    public void test_exerciseOption_noOption() {
        Long id = 1L;
        Optional<Option> option = Optional.ofNullable(null);
        when(mockOptionRepo.findById(id)).thenReturn(option);
        optionController.exerciseOption(id);
        verify(mockOptionRepo, times(1)).findById(id);
    }
}
