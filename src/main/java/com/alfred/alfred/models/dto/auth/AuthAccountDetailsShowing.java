package com.alfred.alfred.models.dto.auth;

import java.util.*;

public class AuthAccountDetailsShowing {
    private String email;
    private String password;
    private List<Map<String, String>> roles;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(List<Map<String, String>> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Map<String, String>> getRoles() {
        return roles;
    }
}
