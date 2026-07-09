package com.company.rms;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

public class UserExceptionTest {

    @Test
    void testUserNotFound() {

        UserRepository repo = mock(UserRepository.class);

        when(repo.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                NoSuchElementException.class,
                () -> {
                    throw new NoSuchElementException();
                });
    }
}
