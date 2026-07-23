package com.company.rms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CalculatorServiceTest {

    CalculatorService service = new CalculatorService();

    @Test
    void testAdd() {
        assertEquals(10, service.add(4, 6));
    }
}
