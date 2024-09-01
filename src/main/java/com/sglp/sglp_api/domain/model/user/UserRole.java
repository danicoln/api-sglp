package com.sglp.sglp_api.domain.model.user;

public enum UserRole {

    ADMIN("amin"),
    USER("user");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
