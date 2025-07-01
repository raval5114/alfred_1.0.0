package com.alfred.alfred.controller;

import com.alfred.alfred.models.User;
import com.alfred.alfred.models.dto.auth.AuthSiginResponse;
import com.alfred.alfred.models.dto.auth.AuthSignRequest;
import com.alfred.alfred.models.dto.auth.AuthSignUpResponse;
import com.alfred.alfred.models.dto.auth.tokenBody.TokenBody;
import com.alfred.alfred.service.AuthService;
import com.alfred.alfred.utils.JwtToken;

import ch.qos.logback.core.subst.Token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthSiginResponse> signIn(@RequestBody AuthSignRequest request) {
        JwtToken genToken = new JwtToken();
        String token;
        AuthSiginResponse response = new AuthSiginResponse();
        try {
            boolean isValid = authService.signIn(request.getEmail(), request.getPassword());

            if (isValid) {
                TokenBody tokenBody = new TokenBody(authService.returnId(request.getEmail()),
                        authService.returnRoles(request.getEmail()));
                // TokenBody(TokenBody(authService.ReturnId(request.getEmail()));
                token = genToken.generateToken(tokenBody);
                response.setMessage("Login successful");

                response.setToken(token);
                return ResponseEntity.ok(response);
            } else {
                response.setMessage("Invalid email or password");
                return ResponseEntity.status(401).body(response);
            }

        } catch (Exception ex) {
            response.setMessage("Error: " + ex.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthSignUpResponse> signUp(@RequestBody User user) {
        AuthSignUpResponse response = new AuthSignUpResponse();
        try {
            boolean isRegistered = authService.signUp(user);

            if (isRegistered) {
                response.setMessage("User registered successfully");
                return ResponseEntity.ok(response);
            } else {
                response.setMessage("User with this email already exists");
                return ResponseEntity.status(409).body(response);
            }

        } catch (Exception ex) {
            response.setMessage("Error: " + ex.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
}
