package com.alfred.alfred.models.dto.auth.tokenBody;

import java.util.List;
import java.util.Map;

public class TokenBody {

    private String id;
    private List<Map<String, String>> roles;

    public TokenBody(String id, List<Map<String, String>> roles) {
        this.id = id;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Map<String, String>> getData() {
        return roles;
    }

    public void setData(List<Map<String, String>> data) {
        this.roles = data;
    }

}
