package com.goup.entities.usuarios;

public enum UserRole {
    VENDEDOR("Vendedor"),
    GERENTE("Gerente"),
    ADMIN("ADMIN");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
