package MODULE_1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CalculatorTests {

    @Test
    void additionTest() {
        assertEquals(10, 5 + 5);
    }

    @Test
    void subtractionTest() {
        assertEquals(5, 10 - 5);
    }
}