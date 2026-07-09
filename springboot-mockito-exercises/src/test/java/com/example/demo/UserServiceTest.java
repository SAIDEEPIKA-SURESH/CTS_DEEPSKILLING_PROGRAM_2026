package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    @Test
    void testGetUserById() {

        User user =
                new User(1L,
                        "John");

        when(repository
                .findById(1L))
                .thenReturn(
                        Optional.of(user));

        User result =
                service.getUserById(1L);

        assertNotNull(result);

        assertEquals(
                "John",
                result.getName());

        verify(repository)
                .findById(1L);
    }
}
