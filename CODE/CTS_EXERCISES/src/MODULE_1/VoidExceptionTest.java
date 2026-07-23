package MODULE_1;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

public class VoidExceptionTest {

    @Test
    public void testVoidException() {

        ExternalApi mockApi = mock(ExternalApi.class);

        doThrow(new RuntimeException("Error"))
                .when(mockApi)
                .deleteData();

        assertThrows(
                RuntimeException.class,
                () -> mockApi.deleteData()
        );

        verify(mockApi).deleteData();
    }
}
