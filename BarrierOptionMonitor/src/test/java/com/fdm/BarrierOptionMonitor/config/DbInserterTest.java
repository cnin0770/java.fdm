package com.fdm.BarrierOptionMonitor.config;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fdm.BarrierOptionMonitor.dal.AccountRepo;
import com.fdm.BarrierOptionMonitor.dal.ClientRepo;
import com.fdm.BarrierOptionMonitor.dal.NotificationRepo;
import com.fdm.BarrierOptionMonitor.dal.OptionRepo;
import com.fdm.BarrierOptionMonitor.dal.StaffRepo;
import com.fdm.BarrierOptionMonitor.model.Client;
import com.fdm.BarrierOptionMonitor.model.Notifications;
import com.fdm.BarrierOptionMonitor.model.Option;
import com.fdm.BarrierOptionMonitor.model.Staff;

public class DbInserterTest {

    @Mock
    AccountRepo mockAccountRepo;
    @Mock
    OptionRepo mockOptionRepo;
    @Mock
    ClientRepo mockClientRepo;
    @Mock
    NotificationRepo mockNotificationRepo;
    @Mock
    StaffRepo mockStaffRepo;

    DbInserter dbInserter;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        dbInserter = new DbInserter(mockAccountRepo, mockOptionRepo, mockClientRepo, mockNotificationRepo, mockStaffRepo);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void test_dbInserter_addsAllData_toDatabase() throws Exception {
        List<Staff> foundStaff = new ArrayList<Staff>();
        List<Staff> foundStaff2 = new ArrayList<Staff>();
        Client client = Client.builder().name("Tim").build();
        Client client2 = Client.builder().name("Chuan").build();
        Staff user1 = new Staff();
        user1.setName("user");
        user1.setPassword("user");
        Staff admin = new Staff();
        admin.setName("admin");
        admin.setPassword("admin");
        foundStaff2.add(user1);
        foundStaff2.add(admin);
        Notifications notification = new Notifications();
        notification.setStaff(user1);
        Notifications notification2 = new Notifications();
        notification2.setStaff(admin);
        Option option = Option.builder()
                .isAmerican(true)
                .isCall(true)
                .isKnockIn(true)
                .quantity(2)
                .strikePrice(10)
                .expirationDate("2021-04-30")
                .barrierDirection(true)
                .barrierLevel(20)
                .stockSymbol("ANZ.AX")
                .client(client)
                .build();
        Option option2 = Option.builder()
                .isAmerican(false)
                .isCall(false)
                .isKnockIn(true)
                .quantity(10)
                .strikePrice(1000)
                .expirationDate("2020-07-30")
                .barrierDirection(true)
                .barrierLevel(10)
                .stockSymbol("WBC.AX")
                .client(client)
                .build();
        Option option3 = Option.builder()
                .isAmerican(true)
                .isCall(false)
                .isKnockIn(true)
                .quantity(10)
                .strikePrice(2000)
                .expirationDate("2020-09-30")
                .barrierDirection(false)
                .barrierLevel(100)
                .stockSymbol("MQG.AX")
                .client(client2)
                .build();
        Option option4 = Option.builder()
                .isAmerican(true)
                .isCall(true)
                .isKnockIn(true)
                .quantity(100)
                .strikePrice(29)
                .expirationDate("2020-05-04")
                .barrierDirection(true)
                .barrierLevel(60)
                .stockSymbol("CBA.AX")
                .client(client)
                .build();
        when(mockStaffRepo.findAll()).thenReturn(foundStaff, foundStaff2);
        dbInserter.run();
        verify(mockStaffRepo, times(2)).findAll();
        verify(mockClientRepo, times(1)).save(client);
        verify(mockClientRepo, times(1)).save(client2);
        verify(mockStaffRepo, times(1)).save(user1);
        verify(mockStaffRepo, times(1)).save(admin);
        verify(mockNotificationRepo, times(1)).save(notification);
        verify(mockNotificationRepo, times(1)).save(notification2);
        verify(mockOptionRepo, times(1)).save(option);
        verify(mockOptionRepo, times(1)).save(option2);
        verify(mockOptionRepo, times(1)).save(option3);
        verify(mockOptionRepo, times(1)).save(option4);
    }

    @Test
    public void test_dbInserter_addNothingToDB() throws Exception {
        List<Staff> foundStaff = new ArrayList<Staff>();
        foundStaff.add(new Staff());
        when(mockStaffRepo.findAll()).thenReturn(foundStaff);
        dbInserter.run();
        verify(mockStaffRepo, times(1)).findAll();
    }
}
