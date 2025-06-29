package com.alfred.alfred.models.dto.auth;

public class AuthSignRequest {
    private String email;
    private String password;

    public AuthSignRequest() {
    };

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
