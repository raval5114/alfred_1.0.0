package com.alfred.alfred.models.dto;

public class requestBody {

    private String message;
    private String mode;

    public requestBody() {
    }

    public requestBody(String message, String mode) {
        this.message = message;
        this.mode = mode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
