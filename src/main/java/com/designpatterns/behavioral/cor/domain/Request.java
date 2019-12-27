package com.designpatterns.behavioral.cor.domain;

public class Request {

    private String username;
    private String password;
    private String requestedResource;

    public Request(String username, String password, String requestedResource) {
        this.username = username;
        this.password = password;
        this.requestedResource = requestedResource;
    }

    public String getRequestedResource() {
        return requestedResource;
    }

    public void setRequestedResource(String requestedResource) {
        this.requestedResource = requestedResource;
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
