package com.fdm.w6.currencyconverter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
    private iEcbCurrencyReader ecb_reader;

    @Before
    public void initTest() {
        ecb_reader = new EcbCurrencyReader();
    }

    @Test
    public void load_not_null() {
        ecb_reader.handle();
        assertNotNull(ecb_reader);
    }
}
