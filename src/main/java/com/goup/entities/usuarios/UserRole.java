package com.goup.entities.usuarios;

public enum UserRole {
    CREATE("create"),
    READ("read"),
    UPDATE("update"),
    DELETE("delete");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
