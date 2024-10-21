package com.exemplo.userregistration.service;

import com.exemplo.userregistration.model.User;
import com.exemplo.userregistration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user, String username) {
        user.setDataCriacao(LocalDateTime.now());
        user.setUsuarioCriacao(username);
        return userRepository.save(user);
    }

    public Optional<User> findUserByCpf(String cpf) {
        return userRepository.findByCpf(cpf);
    }

    public User updateUser(User user, String username) {
        user.setDataAtualizacao(LocalDateTime.now());
        user.setUsuarioAtualizacao(username);
        return userRepository.save(user);
    }

    public void deleteUser(User user, String username) {
        user.setStatus("Removido");
        user.setDataRemocao(LocalDateTime.now());
        user.setUsuarioRemocao(username);
        userRepository.save(user);
    }
}
