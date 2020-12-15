package com.fdmgroup.gradecalculator;

import org.junit.*;

import static org.junit.Assert.*;

public class GradeCalculatorServiceTest {
    GradeCalculatorService service;

    @Before
    public void initTest() {
        service = new GradeCalculatorServiceClass();
    }

    @Test
    public void that_returns_fail_when_75() {
        assertEquals("fail", service.getClassification(74));
    }

    @Test
    public void that_returns_pass_when_80() {
        assertEquals("pass", service.getClassification(75));
        assertEquals("pass", service.getClassification(79));
    }

    @Test
    public void that_returns_merit_when_90() {
        assertEquals("merit", service.getClassification(80));
        assertEquals("merit", service.getClassification(89));
    }

    @Test
    public void that_returns_distinction_when_100() {
        assertEquals("distinction", service.getClassification(90));
        assertEquals("distinction", service.getClassification(100));
    }

    @Test
    public void boundary_test() {
        assertEquals("fail", service.getClassification(-1));
        assertEquals("distinction", service.getClassification(102));
    }
}
