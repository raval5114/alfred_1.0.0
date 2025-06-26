package com.alfred.alfred.models.dto;

public class responseBody {

    private String reply;
    private String mode;

    public responseBody() {
    }

    public responseBody(String reply, String mode) {
        this.reply = reply;
        this.mode = mode;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
