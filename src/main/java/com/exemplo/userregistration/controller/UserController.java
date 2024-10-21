package com.exemplo.userregistration.controller;

import com.exemplo.userregistration.model.User;
import com.exemplo.userregistration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user, Authentication auth) {
        String username = auth.getName(); // Username from JWT
        return ResponseEntity.ok(userService.createUser(user, username));
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<User> getUserByCpf(@PathVariable String cpf) {
        Optional<User> user = userService.findUserByCpf(cpf);
        return user.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<User> updateUser(@PathVariable String cpf, @RequestBody User updatedUser, Authentication auth) {
        String username = auth.getName();
        Optional<User> existingUser = userService.findUserByCpf(cpf);
        if (existingUser.isPresent()) {
            updatedUser.setId(existingUser.get().getId());
            return ResponseEntity.ok(userService.updateUser(updatedUser, username));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deleteUser(@PathVariable String cpf, Authentication auth) {
        String username = auth.getName();
        Optional<User> user = userService.findUserByCpf(cpf);
        if (user.isPresent()) {
            userService.deleteUser(user.get(), username);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
