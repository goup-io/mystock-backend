package com.goup.entities.estoque;

import com.goup.entities.estoque.produtos.Produto;
import com.goup.entities.lojas.Loja;
import com.goup.entities.vendas.ItemPromocional;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity @Getter @Setter
@Table(uniqueConstraints = {
        @UniqueConstraint( columnNames = {"tamanho", "produto", "loja"}),
        @UniqueConstraint( columnNames = {"codigo", "loja_id"} )
})
public class ETP {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String codigo;

    @NotNull @ManyToOne @JoinColumn(name = "tamanho_id", referencedColumnName = "id")
    private Tamanho tamanho;

    @NotNull @ManyToOne @JoinColumn(name="produto_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Produto produto;

    @NotNull @ManyToOne @JoinColumn(name = "loja_id", referencedColumnName = "id")
    private Loja loja;

    @Enumerated(EnumType.STRING)
    private ItemPromocional itemPromocional;

    @NotNull @PositiveOrZero @Column
    private int quantidade;
}