package com.goup.entities.lojas;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class AcessoLoja {
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id
    private Integer id;
    @Column @NotNull
    private TipoLogin tipo;
    @Column @NotNull
    private String descricao;

    public Integer getId() {
        return id;
    }

    public TipoLogin getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTipo(TipoLogin tipo) {
        this.tipo = tipo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
