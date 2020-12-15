package com.fdm.BarrierOptionMonitor.controller;

import com.fdm.BarrierOptionMonitor.dal.AccountRepo;
import com.fdm.BarrierOptionMonitor.model.Client;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.fdm.BarrierOptionMonitor.dal.OptionRepo;
import com.fdm.BarrierOptionMonitor.dal.ClientRepo;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientControllerTest {

    @Mock
    ClientRepo mockClientRepo;
    @Mock
    OptionRepo mockOptionRepo;
    @Mock
    AccountRepo mockAccountRepo;

    ClientController clientController;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        clientController = new ClientController(mockClientRepo, mockOptionRepo, mockAccountRepo);
    }

    @Test
    public void test_getAllClients_returnsListOfClients() {
        List<Client> foundClients = new ArrayList<Client>();
        foundClients.add(new Client());
        when(mockClientRepo.findAll()).thenReturn(foundClients);
        clientController.getAllClients();
        verify(mockClientRepo, times(1)).findAll();
        assertNotNull(foundClients);
    }

    @Test
    public void test_getAllClients_returnsEmptyListOfClients() {
        List<Client> foundClients = new ArrayList<Client>();
        when(mockClientRepo.findAll()).thenReturn(foundClients);
        clientController.getAllClients();
        verify(mockClientRepo, times(1)).findAll();
        assertNotNull(foundClients);
        assertTrue(foundClients.isEmpty());
    }

    @Test
    public void test_savingClient_savesClient() {
        Client passedClient = new Client();
        clientController.save(passedClient);
        verify(mockClientRepo, times(1)).save(passedClient);
    }

    @Test
    public void test_returnsClient_whenPassID() {
        Long id = 1L;
        Optional<Client> foundClient = Optional.ofNullable(new Client());
        when(mockClientRepo.findById(id)).thenReturn(foundClient);
        clientController.getClients(id);
        verify(mockClientRepo, times(1)).findById(id);
        assertNotNull(foundClient);
    }

    @Test
    public void test_returnsNull_whenPassID() {
        Long id = 1L;
        Optional<Client> foundClient = Optional.ofNullable(null);
        when(mockClientRepo.findById(id)).thenReturn(foundClient);
        clientController.getClients(id);
        verify(mockClientRepo, times(1)).findById(id);
        assertNotNull(foundClient);
    }

    @Test
    public void test_optionIsDeleted_byPassedID() {
        Long id = 1L;
        clientController.get(id);
        verify(mockOptionRepo, times(1)).CustomRemoveOptionForClient(id);
        verify(mockClientRepo, times(1)).deleteById(id);
    }
}
