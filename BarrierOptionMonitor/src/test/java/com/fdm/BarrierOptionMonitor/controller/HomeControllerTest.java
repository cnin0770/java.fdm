package com.fdm.BarrierOptionMonitor.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;


import java.security.Principal;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomeControllerTest {
    /*
    please do before testing:
        1. disable security : custom.security.enabled=false
        2. stop running website.
     */

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private HomeController controller;

    @Mock
    private Principal mockPrinciple;

    @Mock
    private Model mockModel;

    @Test
    public void that_controller_not_null() {
        assertNotNull(controller);
        assertTrue(template.getForObject("http://localhost:" + port + "/", String.class).contains("i"));
    }

    @Test
    public void that_controller_passes_principle() {
        Principal principal = new Principal() {

            @Override
            public String getName() {
                // TODO Auto-generated method stub
                return "tim";
            }
        };
        controller.securedIndex(principal, mockModel);
        verify(mockModel, times(1)).addAttribute("username", principal.getName());
    }

    @Test
    public void that_controller_passes_emptyPrincipal() {
        Principal principal = null;
        controller.securedIndex(principal, mockModel);
        verify(mockModel, times(1)).addAttribute("username", "");
    }

    @Test
    public void test_thatGetIndex_ReturnsIndex() {
        controller.index();
    }

    @Test
    public void test_returns_addOptions() {
        controller.getAddOptions();
    }

    @Test
    public void test_returns_getViewOptions() {
        controller.getViewOptions();
    }

    @Test
    public void test_returns_getViewClients() {
        controller.getViewClients();
    }

    @Test
    public void test_returns_viewStocks() {
        controller.viewStocks();
    }
}
