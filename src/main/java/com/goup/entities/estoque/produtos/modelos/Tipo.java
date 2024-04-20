package com.goup.entities.estoque.produtos.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;
import lombok.Getter;

@Entity @Getter @Setter
public class Tipo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank @NotNull @Column(unique = true)
    private String nome;
}
