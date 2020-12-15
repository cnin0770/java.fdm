package com.fdm.BarrierOptionMonitor.config;

import com.fdm.BarrierOptionMonitor.dal.AccountRepo;
import com.fdm.BarrierOptionMonitor.dal.NotificationRepo;
import com.fdm.BarrierOptionMonitor.dal.OptionRepo;
import com.fdm.BarrierOptionMonitor.dal.StaffRepo;
import com.fdm.BarrierOptionMonitor.dal.ClientRepo;
import com.fdm.BarrierOptionMonitor.model.Client;
import com.fdm.BarrierOptionMonitor.model.Notifications;
import com.fdm.BarrierOptionMonitor.model.Option;
import com.fdm.BarrierOptionMonitor.model.Staff;

import lombok.extern.log4j.Log4j2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class DbInserter implements CommandLineRunner {
    AccountRepo accountRepo;
    OptionRepo optionRepo;
    ClientRepo clientRepo;
    NotificationRepo notificationRepo;
    StaffRepo staffRepo;

    @Autowired
    public DbInserter(AccountRepo accountRepo, OptionRepo optionRepo, ClientRepo clientRepo,
                      NotificationRepo notificationRepo,
                      StaffRepo staffRepo) {
        this.accountRepo = accountRepo;
        this.optionRepo = optionRepo;
        this.clientRepo = clientRepo;
        this.notificationRepo = notificationRepo;
        this.staffRepo = staffRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Staff> foundStaff = staffRepo.findAll();
        if (foundStaff.isEmpty()) {
            Client client = Client.builder().name("Tim").build();
            Client client2 = Client.builder().name("Chuan").build();
            clientRepo.save(client);
            clientRepo.save(client2);
            Staff user1 = new Staff();
            user1.setName("user");
            user1.setPassword("user");
            Staff admin = new Staff();
            staffRepo.save(user1);
            admin.setName("admin");
            admin.setPassword("admin");
            staffRepo.save(admin);
            List<Staff> staff = staffRepo.findAll();
            for (Staff st : staff) {
                Notifications notification = new Notifications();
                notification.setStaff(st);
                notificationRepo.save(notification);
                log.info(st.getName());
            }
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
            optionRepo.save(option);
            optionRepo.save(option2);
            optionRepo.save(option3);
            optionRepo.save(option4);
        }
    }
}
