package MODULE_1;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

public class OrderVerificationTest {

    @Test
    public void testOrder() {

        ExternalApi mockApi = mock(ExternalApi.class);

        mockApi.getData();
        mockApi.saveData("ABC");

        InOrder order = inOrder(mockApi);

        order.verify(mockApi).getData();
        order.verify(mockApi).saveData("ABC");
    }
}
