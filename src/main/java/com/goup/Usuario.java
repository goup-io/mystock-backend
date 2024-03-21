package com.goup;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public abstract class Usuario {
    private int id;
    private int codigoVenda;
    @Size(min = 3, max = 30)
    @NotBlank
    private String nome;
    //@Pattern(regexp = "Gerente geral")
    //@Pattern(regexp = "Gerente")
   // @Pattern(regexp = "Vendedor")
    @NotBlank
    private String cargo;
    @Size(min = 9, max = 15)
    @NotBlank
    private String telefone;


    public Usuario() {
    }

    public Usuario(int id, int codigoVenda, String nome, String cargo, String telefone) {
        this.id = id;
        this.codigoVenda = codigoVenda;
        this.nome = nome;
        this.cargo = cargo;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodigoVenda() {
        return codigoVenda;
    }

    public void setCodigoVenda(int codigoVenda) {
        this.codigoVenda = codigoVenda;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
