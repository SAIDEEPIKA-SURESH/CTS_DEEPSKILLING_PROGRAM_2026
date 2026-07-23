package MODULE_1;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        CalculatorTests.class,
        StringTests.class
})
public class AllTests {
}
