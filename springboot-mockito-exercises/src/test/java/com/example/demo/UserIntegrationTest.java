package com.example.demo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testIntegration()
            throws Exception {

        User user =
                new User(
                        1L,
                        "John");

        when(userService
                .getUserById(1L))
                .thenReturn(user);

        mockMvc.perform(
                get("/users/1"))
                .andExpect(
                        status().isOk())
                .andExpect(
                        jsonPath("$.id")
                                .value(1))
                .andExpect(
                        jsonPath("$.name")
                                .value("John"));
    }
}
