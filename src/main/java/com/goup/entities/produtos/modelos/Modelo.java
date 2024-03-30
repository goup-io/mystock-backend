package com.goup.entities.produtos.modelos;

import com.goup.entities.produtos.Cor;
import com.goup.multiple_pk.ModeloPK;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table
@IdClass(ModeloPK.class) // Indica que a PK é representada por uma classe (Devido as multiplas PKs)
public class Modelo {
    @Id
    private int id;

    @Column(unique = true)
    private String codigo;

    @NotBlank @Column
    private String nome;

    @NotBlank @Column
    private String material;

    @ManyToOne @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private Categoria categoria;

    @ManyToOne @JoinColumn(name="tipo_id", referencedColumnName="id")
    private Tipo tipo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}
