package com.company.rms;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
public class UserPostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    UserService userService;

    @Test
    void testCreateUser() throws Exception {

        User user = new User();
        user.setId(1L);
        user.setName("John");

        when(userService.saveUser(any(User.class)))
                .thenReturn(user);

        mockMvc.perform(post("/users")
                .contentType("application/json")
                .content("""
                        {
                          "name":"John"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name")
                .value("John"));
    }
}
