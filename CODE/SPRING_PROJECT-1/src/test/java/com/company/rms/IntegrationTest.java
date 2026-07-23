package com.company.rms;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class IntegrationTest {

    @Autowired
    private UserService userService;

    @Test
    void testIntegration() {

        User user = new User();
        user.setName("John");

        User saved = userService.saveUser(user);

        assertNotNull(saved);
    }
}
