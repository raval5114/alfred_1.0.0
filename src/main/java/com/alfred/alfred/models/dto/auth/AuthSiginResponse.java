package com.alfred.alfred.models.dto.auth;

public class AuthSiginResponse {
    private String message;
    private String token;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }
}
