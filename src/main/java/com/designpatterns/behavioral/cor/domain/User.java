package com.designpatterns.behavioral.cor.domain;

import java.util.List;

public class User {
    String username;
    String password;
    List<String> validResources;

    public User(String username, String password, List<String> validResources) {
        this.username = username;
        this.password = password;
        this.validResources = validResources;
    }

    public List<String> getValidResources() {
        return validResources;
    }

    public void setValidResources(List<String> validResources) {
        this.validResources = validResources;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
