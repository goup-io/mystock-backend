package com.goup.entities.cargos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class Cargo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column @NotBlank
    private String nome;
    @Column
    private String descricao;
}
