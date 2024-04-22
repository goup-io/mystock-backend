package com.goup.entities.lojas;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

@Entity @Getter @Setter
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
    @Column
    private String complemento;
}
