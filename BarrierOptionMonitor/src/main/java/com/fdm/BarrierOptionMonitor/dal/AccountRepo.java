package com.fdm.BarrierOptionMonitor.dal;

import com.fdm.BarrierOptionMonitor.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fdm.BarrierOptionMonitor.model.Account;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AccountRepo extends JpaRepository<Account, Long> {
    List<Account> getAccountsByClient(Client client);

    Account getAccountByClientAndCurrency(Client client, String currency);

    @Transactional
    @Modifying
    @Query("delete from Account o where o.client.id =:clientID")
    void CustomRemoveAccountForClient(@Param("clientID") long id);

    @Query("select a from Account a where a.client.id=:clientID")
    List<Account> CustomFindByClientID(@Param("clientID") long id);
}
