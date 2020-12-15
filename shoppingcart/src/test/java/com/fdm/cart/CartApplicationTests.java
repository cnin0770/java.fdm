package com.fdm.cart;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertTrue;

@Slf4j
@SpringBootTest
class CartApplicationTests {

	@Test
	void contextLoads() {
		assertTrue(true);
		log.info("logging successfully. junit successfully.");
	}
}
