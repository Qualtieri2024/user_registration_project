
package com.exemplo.userregistration.controller;

import com.exemplo.userregistration.model.AuthenticationRequest;
import com.exemplo.userregistration.model.AuthenticationResponse;
import com.exemplo.userregistration.security.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AuthControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @Mock
    private UserDetailsService userDetailsService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLogin() {
        AuthenticationRequest request = new AuthenticationRequest("admin", "password");
        UserDetails userDetails = mock(UserDetails.class);
        String token = "jwt-token";

        when(userDetailsService.loadUserByUsername("admin")).thenReturn(userDetails);
        when(jwtTokenUtil.generateToken(userDetails)).thenReturn(token);

        AuthenticationResponse response = authController.login(request);

        assertEquals(token, response.getJwt());
    }
}
