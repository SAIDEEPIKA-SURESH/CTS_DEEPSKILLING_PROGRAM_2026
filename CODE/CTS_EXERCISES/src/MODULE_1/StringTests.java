package MODULE_1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class StringTests {

    @Test
    void testLength() {
        assertEquals(5, "Hello".length());
    }

    @Test
    void testContains() {
        assertTrue("Java".contains("av"));
    }
}
