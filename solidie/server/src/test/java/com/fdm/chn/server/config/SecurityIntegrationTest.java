package com.fdm.chn.server.config;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = RANDOM_PORT)
public class SecurityIntegrationTest {
    TestRestTemplate restTemplate;
    URL base;
//    @LocalServerPort
    int port;

//    @Before
    public void init() throws MalformedURLException {
        restTemplate = new TestRestTemplate("user", "password");
        base = new URL("http://localhost:" + port);
    }

    public void that_right_login_info_should_pass() throws IllegalStateException {
        ResponseEntity<String> response = restTemplate.getForEntity(base.toString(), String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.toString().contains("Welcome"));
    }

    public void that_wrong_login_info_should_deny() {
        restTemplate = new TestRestTemplate("user", "");
        ResponseEntity<String> response = restTemplate.getForEntity(base.toString(), String.class);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertTrue(response.toString().contains("DENY"));
    }
}
