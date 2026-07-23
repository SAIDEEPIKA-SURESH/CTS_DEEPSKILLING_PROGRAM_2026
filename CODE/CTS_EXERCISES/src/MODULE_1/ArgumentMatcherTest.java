package MODULE_1;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

public class ArgumentMatcherTest {

    @Test
    public void testArgumentMatching() {

        ExternalApi mockApi = mock(ExternalApi.class);

        MyService service = new MyService(mockApi);

        service.processUser("John", 25);

        verify(mockApi)
                .process(eq("John"), anyInt());
    }
}
