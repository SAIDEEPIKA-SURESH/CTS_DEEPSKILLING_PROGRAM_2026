package MODULE_1;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ExceptionThrowerTest {

    ExceptionThrower obj = new ExceptionThrower();

    @Test
    void testException() {

        assertThrows(
                ArithmeticException.class,
                () -> obj.throwException()
        );
    }
}
