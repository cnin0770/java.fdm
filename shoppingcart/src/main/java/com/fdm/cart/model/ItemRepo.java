package com.fdm.cart.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item, Long> {
}
