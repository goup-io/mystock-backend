package com.goup.entities.usuarios.login;

public enum UserRole {
    VENDEDOR("Vendedor"),
    GERENTE("Gerente"),
    ADMIN("Admin");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
