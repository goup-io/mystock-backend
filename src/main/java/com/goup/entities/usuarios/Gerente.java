package com.goup.entities.usuarios;

import com.goup.entities.cargos.Cargo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Gerente {

    @NotBlank
    @Email
    private String email;
    @Size(min = 4, max = 10)
    @NotBlank
    private String senha;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
