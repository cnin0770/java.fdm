package com.fdm.cart.controller;

import com.fdm.cart.model.Item;
import com.fdm.cart.model.ItemRepo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class ItemControllerTests {

    @Mock
    ItemRepo mockItemRepo;

    ItemController itemController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        itemController = new ItemController(mockItemRepo);
    }

    @Test
    public void that_getItemById_returns_Item() {
        long mockId = 1L;
        Optional found = Optional.of(new Item());
        when(mockItemRepo.findById(mockId)).thenReturn(found);

        itemController.getItemById(mockId);

        verify(mockItemRepo, times(1)).findById(mockId);
        assertNotNull(found);
    }
}
