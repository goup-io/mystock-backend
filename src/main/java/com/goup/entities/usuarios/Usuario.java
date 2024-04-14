package com.goup.entities.usuarios;

import com.goup.entities.cargos.Cargo;
import com.goup.entities.lojas.Loja;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table
public class Usuario {
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id
    private Integer id;
    @Column(unique = true)
    private Integer codigoVenda;
    @Size(min = 3, max = 30) @NotBlank @Column
    private String nome;
    @NotNull @JoinColumn @ManyToOne(cascade = CascadeType.PERSIST)
    private Cargo cargo;
    @Column @Email
    private String email;
    @NotNull @Column @Pattern(regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$", message = "Número de celular inválido")
    private String telefone;
    @NotNull @JoinColumn @ManyToOne(cascade = CascadeType.PERSIST)
    private Loja loja;

    public Usuario(Integer codigoVenda, String nome, Cargo cargo, String email, String telefone, Loja loja) {
        this.codigoVenda = codigoVenda;
        this.nome = nome;
        this.cargo = cargo;
        this.email = email;
        this.telefone = telefone;
        this.loja = loja;
    }

    public Usuario() {
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

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCodigoVenda(Integer codigoVenda) {
        this.codigoVenda = codigoVenda;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
