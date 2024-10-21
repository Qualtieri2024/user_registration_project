
package com.exemplo.userregistration.controller;

import com.exemplo.userregistration.model.AuthenticationRequest;
import com.exemplo.userregistration.model.AuthenticationResponse;
import com.exemplo.userregistration.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return new AuthenticationResponse(jwt);
    }
}
