package com.goup.entities.estoque.produtos.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nome", "categoria_id", "tipo_id"}),
        @UniqueConstraint(columnNames = {"nome", "categoria_id"}),
        @UniqueConstraint(columnNames = {"nome", "tipo_id"})
})
public class Modelo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull @ManyToOne @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private Categoria categoria;

    @NotNull @ManyToOne @JoinColumn(name="tipo_id", referencedColumnName="id")
    private Tipo tipo;

    @NotBlank @NotNull // NotBlank não define a coluna como NOT NULL
    private String nome;
}
