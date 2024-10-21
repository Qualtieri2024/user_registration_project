
package com.exemplo.userregistration.service;

import com.exemplo.userregistration.model.User;
import com.exemplo.userregistration.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindUserByCpf() {
        User mockUser = new User();
        mockUser.setCpf("12345678901");
        mockUser.setNome("John Doe");

        when(userRepository.findByCpf("12345678901")).thenReturn(Optional.of(mockUser));

        Optional<User> foundUser = userService.findUserByCpf("12345678901");
        assertTrue(foundUser.isPresent());
        assertEquals("John Doe", foundUser.get().getNome());
    }

}
