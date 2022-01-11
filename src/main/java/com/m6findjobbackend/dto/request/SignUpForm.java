package com.m6findjobbackend.dto.request;

import java.util.Set;

public class SignUpForm {
    private String username;
    private Set<String> roles;

    public SignUpForm() {
    }

    public SignUpForm(String username, Set<String> roles) {
        this.username = username;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
