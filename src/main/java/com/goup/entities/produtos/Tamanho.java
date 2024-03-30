package com.goup.entities.produtos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity @Table
public class Tamanho {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank @Column(unique = true)
    private int numero;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
