package com.goup.entities.estoque.produtos;

import com.goup.entities.estoque.produtos.modelos.Modelo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"cor", "modelo"})})
public class Produto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;

    @NotNull @ManyToOne @JoinColumn(name="cor_id", referencedColumnName="id")
    private Cor cor;

    @NotNull @ManyToOne @JoinColumn(name="modelo_id", referencedColumnName="id")
    private Modelo modelo;

    @NotNull @Positive
    private Double valorCusto;

    @NotNull @Positive
    private Double valorRevenda;
}