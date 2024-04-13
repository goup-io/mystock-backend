package com.goup.entities.lojas;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CNPJ;

@Entity
@Table
public class Loja {
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id
    private Integer id;
    @Column(unique = true) @NotNull
    private String nome;
    @Column(unique = true) @CNPJ @NotNull
    private String cnpj;
    @Column @Pattern(regexp = "[0-9]{5}[-]?[0-9]{3}") @NotNull
    private String cep;
    @Column @NotNull
    private Integer numero;
    @Column @NotNull
    private String complemento;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
