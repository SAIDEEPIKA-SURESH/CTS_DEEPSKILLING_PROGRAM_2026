package MODULE_1;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

public class VerifyInteractionTest {

    @Test
    public void testVerifyInteraction() {

        ExternalApi mockApi = mock(ExternalApi.class);

        MyService service = new MyService(mockApi);

        service.fetchData();

        verify(mockApi).getData();
    }
}
