package com.goup.entities.usuarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Gerente extends Usuario{

    @NotBlank
    @Email
    private String email;
    @Size(min = 4, max = 10)
    @NotBlank
    private String senha;


    public Gerente() {
    }

    public Gerente(int id, int codigoVenda, String nome, String cargo, String telefone, String email, String senha) {
        super(id, codigoVenda, nome, cargo, telefone);
        this.email = email;
        this.senha = senha;
    }

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
