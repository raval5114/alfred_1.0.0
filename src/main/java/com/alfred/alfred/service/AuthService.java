package com.alfred.alfred.service;

import com.alfred.alfred.models.User;
import com.alfred.alfred.repositories.authReponsitory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    private final authReponsitory authRepository;

    @Autowired
    public AuthService(authReponsitory authRepository) {
        this.authRepository = authRepository;
    }

    public boolean signUp(User user) {
        try {
            Optional<User> existingUser = authRepository.findByEmail(user.getEmail());
            if (existingUser.isPresent()) {
                System.out.println("User already exists: " + user.getEmail());
                return false;
            }
            System.out.println("Saving user: " + user);
            authRepository.save(user);
            return true;
        } catch (Exception e) {
            System.out.println("Error saving user: " + e.getMessage());
            return false;
        }
    }

    public String returnId(String email) {
        return authRepository.findByEmail(email)
                .map(User::getId)
                .orElse(null);
    }

    public List<Map<String, String>> returnRoles(String email) {
        return authRepository.findByEmail(email)
                .map(User::getData)
                .orElse(null);
    }

    public boolean signIn(String email, String password) {
        return authRepository.findByEmail(email)
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }
}
