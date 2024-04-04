package com.goup.entities.usuarios;

import com.goup.entities.cargos.Cargo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table
public class Usuario {
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id
    private Integer id;
    @Column(unique = true)
    private Integer codigoVenda;
    @Size(min = 3, max = 30) @NotBlank @Column
    private String nome;
    @NotBlank @JoinColumn @ManyToOne
    private Cargo cargo;
    //@Pattern()
    @NotBlank @Column
    private String telefone;

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

}
