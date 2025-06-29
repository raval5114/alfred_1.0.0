package com.alfred.alfred.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alfred.alfred.models.User;
import com.alfred.alfred.repositories.authReponsitory;

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

    public String ReturnId(String email) {
        Optional<User> user = authRepository.findByEmail(email);
        return user.get().getId();
    }

    public List<Map<String, String>> ReturnRoles(String email) {
        Optional<User> user = authRepository.findByEmail(email);
        return user.get().getData();

    }

    public Boolean signUp(User user) {
        try {
            Optional<User> existingUser = authRepository.findByEmail(user.getEmail());
            if (existingUser.isPresent()) {
                return false; // User already exists
            }

            authRepository.save(user);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace(); // Log the exception for debugging
            return false;
        }
    }

    public Boolean signIn(String email, String password) {
        try {
            Optional<User> user = authRepository.findByEmail(email);
            return user.map(value -> value.getPassword().equals(password)).orElse(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
