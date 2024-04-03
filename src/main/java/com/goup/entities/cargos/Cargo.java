package com.goup.entities.cargos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column @NotBlank
    private String nome;
    @Column @NotBlank
    private String descricao;

}
