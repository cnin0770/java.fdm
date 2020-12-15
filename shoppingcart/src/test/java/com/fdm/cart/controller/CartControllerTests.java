package com.fdm.cart.controller;

import com.fdm.cart.model.Cart;
import com.fdm.cart.model.CartRepo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class CartControllerTests {

    @Mock
    CartRepo mockCartRepo;

    CartController cartController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        cartController = new CartController(mockCartRepo);
    }

    @Test
    public void that_getAllCarts_returns_list() {
        List<Cart> found = new ArrayList<>();
        found.add(new Cart());
        when(mockCartRepo.findAll()).thenReturn(found);

        cartController.getAllCarts();

        verify(mockCartRepo, times(1)).findAll();
        assertNotNull(found);
    }
}
