package com.goup.entities.cargos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table
public class Cargo {
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id
    private Integer id;
    @Column @NotBlank
    private String nome;
    @Column @NotBlank
    private String descricao;

}