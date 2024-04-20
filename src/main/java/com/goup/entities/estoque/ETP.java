package com.goup.entities.estoque;

import com.goup.entities.estoque.produtos.Produto;
import com.goup.entities.lojas.Loja;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class ETP {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull @ManyToOne @JoinColumn(name = "tamanho_id", referencedColumnName = "id")
    private Tamanho tamanho;

    @NotNull @ManyToOne @JoinColumn(name="produto_id", referencedColumnName = "id")
    private Produto produto;

    @NotNull @ManyToOne @JoinColumn(name = "loja_id", referencedColumnName = "id")
    private Loja loja;

    @NotNull @PositiveOrZero @Column
    private int quantidade;
}