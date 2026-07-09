package MODULE_1;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CalculatorAAATest {

    Calculator calculator;

    // Setup method
    @Before
    public void setUp() {
        System.out.println("Setup executed");
        calculator = new Calculator();
    }

    // Teardown method
    @After
    public void tearDown() {
        System.out.println("Teardown executed");
        calculator = null;
    }

    @Test
    public void testAddition() {

        // Arrange
        int num1 = 20;
        int num2 = 10;

        // Act
        int result = calculator.add(num1, num2);

        // Assert
        assertEquals(30, result);
    }

    @Test
    public void testSubtraction() {

        // Arrange
        int num1 = 20;
        int num2 = 10;

        // Act
        int result = calculator.subtract(num1, num2);

        // Assert
        assertEquals(10, result);
    }
}
