package com.fdm.cart.config;

import com.fdm.cart.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final ItemStackRepo itemStackRepo;
    private final HostValueRepo hostValueRepo;
    private final ItemRepo itemRepo;
    private final CartRepo cartRepo;

    @Autowired
    public DataLoader(ItemStackRepo itemStackRepo, HostValueRepo hostValueRepo, ItemRepo itemRepo, CartRepo cartRepo) {
        this.itemStackRepo = itemStackRepo;
        this.hostValueRepo = hostValueRepo;
        this.itemRepo = itemRepo;
        this.cartRepo = cartRepo;
    }

    @Override
    public void run(ApplicationArguments args) {
        Cart cart = Cart.builder()
                .label("cart-test")
                .build();

        Item item = Item.builder()
                .name("cup")
                .description("new mark cup.")
                .price(36)
                .inventory(100)
                .build();

        Item item2 = Item.builder()
                .name("cup")
                .description("Brand new mark cup.")
                .price(36)
                .inventory(100)
                .build();

        Item item3 = Item.builder()
                .name("shoe")
                .description("A left shoe alone.")
                .price(18)
                .inventory(100)
                .build();

        Item item4 = Item.builder()
                .name("glove")
                .description("A right glove.")
                .price(40)
                .inventory(100)
                .build();

        Item item5 = Item.builder()
                .name("milk frother")
                .description("Easy frother. Can be used on eggs.")
                .price(9)
                .inventory(100)
                .build();

        ItemStack stack = ItemStack.builder()
                .cart(cart)
                .item(item)
                .quantity(15)
                .build();

        HostValue hostValue = HostValue.builder()
                .currentCartId(1)
                .build();

        itemRepo.save(item);
        itemRepo.save(item2);
        itemRepo.save(item3);
        itemRepo.save(item4);
        itemRepo.save(item5);

        cartRepo.save(cart);

        itemStackRepo.save(stack);
        hostValueRepo.save(hostValue);
    }

}
