package com.goup.entities.estoque.produtos.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class Categoria {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank @NotNull @Column(unique = true) // @NotBLank não deixa Not Null no database
    private String nome;
}
