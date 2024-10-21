
package com.exemplo.userregistration.controller;

import com.exemplo.userregistration.model.User;
import com.exemplo.userregistration.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Mock
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        User mockUser = new User();
        mockUser.setCpf("12345678901");
        mockUser.setNome("John Doe");

        when(authentication.getName()).thenReturn("admin");
        when(userService.createUser(mockUser, "admin")).thenReturn(mockUser);

        ResponseEntity<User> response = userController.createUser(mockUser, authentication);

        assertNotNull(response.getBody());
        assertEquals("John Doe", response.getBody().getNome());
        assertEquals("12345678901", response.getBody().getCpf());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testGetUserByCpf() {
        User mockUser = new User();
        mockUser.setCpf("12345678901");
        mockUser.setNome("John Doe");

        when(userService.findUserByCpf("12345678901")).thenReturn(Optional.of(mockUser));

        ResponseEntity<User> response = userController.getUserByCpf("12345678901");

        assertNotNull(response.getBody());
        assertEquals("John Doe", response.getBody().getNome());
        assertEquals("12345678901", response.getBody().getCpf());
    }

    @Test
    void testUpdateUser() {
        User existingUser = new User();
        existingUser.setCpf("12345678901");
        existingUser.setNome("John Doe");

        User updatedUser = new User();
        updatedUser.setCpf("12345678901");
        updatedUser.setNome("John Updated");

        when(authentication.getName()).thenReturn("admin");
        when(userService.findUserByCpf("12345678901")).thenReturn(Optional.of(existingUser));
        when(userService.updateUser(updatedUser, "admin")).thenReturn(updatedUser);

        ResponseEntity<User> response = userController.updateUser("12345678901", updatedUser, authentication);

        assertNotNull(response.getBody());
        assertEquals("John Updated", response.getBody().getNome());
    }

    @Test
    void testDeleteUser() {
        User mockUser = new User();
        mockUser.setCpf("12345678901");
        mockUser.setNome("John Doe");

        when(authentication.getName()).thenReturn("admin");
        when(userService.findUserByCpf("12345678901")).thenReturn(Optional.of(mockUser));
        doNothing().when(userService).deleteUser(mockUser, "admin");

        ResponseEntity<Void> response = userController.deleteUser("12345678901", authentication);

        assertEquals(204, response.getStatusCodeValue());
        verify(userService, times(1)).deleteUser(mockUser, "admin");
    }
}
