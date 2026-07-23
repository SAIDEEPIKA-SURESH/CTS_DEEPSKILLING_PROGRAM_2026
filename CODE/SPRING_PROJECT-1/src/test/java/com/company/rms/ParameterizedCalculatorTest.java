package com.company.rms;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ParameterizedCalculatorTest {

    CalculatorService service =
            new CalculatorService();

    @ParameterizedTest
    @ValueSource(ints = {2,4,6,8,10})
    void testEvenNumbers(int number) {

        assertEquals(0,
                number % 2);
    }
}
