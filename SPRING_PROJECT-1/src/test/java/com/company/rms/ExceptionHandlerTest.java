package com.company.rms;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
public class ExceptionHandlerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    UserService userService;

    @Test
    void testExceptionHandler() throws Exception {

        when(userService.getUserById(1L))
                .thenThrow(new NoSuchElementException());

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isNotFound())
                .andExpect(content()
                .string("User not found"));
    }
}
