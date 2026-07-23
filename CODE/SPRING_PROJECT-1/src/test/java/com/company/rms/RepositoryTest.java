package com.company.rms;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

@DataJpaTest
public class RepositoryTest {

    @Autowired
    UserRepository repository;

    @Test
    void testFindByName() {

        User user = new User();
        user.setName("John");

        repository.save(user);

        List<User> users =
                repository.findByName("John");

        assertEquals(1, users.size());
    }
}
