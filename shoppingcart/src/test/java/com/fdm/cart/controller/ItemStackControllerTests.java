package com.fdm.cart.controller;

import com.fdm.cart.model.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class ItemStackControllerTests {

    @Mock
    ItemRepo mockItemRepo;
    @Mock
    ItemStackRepo mockStackRepo;
    @Mock
    CartRepo mockCartRepo;

    ItemStackController itemStackController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        itemStackController = new ItemStackController(mockStackRepo, mockCartRepo, mockItemRepo);
    }

    @Test
    public void that_updateItemInCart_proceeds() {
        Item item = Item.builder().id(999L).build();
        Cart cart = Cart.builder().id(999L).build();
        ItemStack stack = ItemStack.builder().id(999L).cart(cart).item(item).build();

        when(mockCartRepo.findById(cart.getId())).thenReturn(Optional.of(cart));
        when(mockItemRepo.findById(item.getId())).thenReturn(Optional.of(item));
        when(mockStackRepo.findItemStackByCartAndItem(cart, item)).thenReturn(stack);

        itemStackController.updateItemInCart(cart.getId(), item.getId(), 10);

        verify(mockCartRepo, times(1)).findById(cart.getId());
        verify(mockItemRepo, times(1)).findById(item.getId());
        verify(mockStackRepo, times(1)).findItemStackByCartAndItem(cart, item);
        verify(mockStackRepo, times(1)).save(stack);
        verify(mockCartRepo, times(1)).save(cart);
    }
}
