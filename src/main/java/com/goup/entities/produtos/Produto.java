package com.goup.entities.produtos;

import com.goup.entities.produtos.modelos.Modelo;
import jakarta.persistence.*;

@Entity
@Table
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name="modelo_id", referencedColumnName="id"),
        @JoinColumn(name="modelo_codigo", referencedColumnName="codigo")
    })
    private Modelo modelo;

    @Column
    private double valorCusto;

    @Column
    private double valorRevenda;

    @Column
    private double valorAtacado;

    @Column
    private double valorCaixa;
}
