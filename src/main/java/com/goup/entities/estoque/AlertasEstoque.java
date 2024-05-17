package com.goup.entities.estoque;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity @Getter @Setter
public class AlertasEstoque {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull @NotBlank
    private String titulo;

    @NotNull @NotBlank
    private String descricao;

    @NotNull
    private LocalDateTime dataHora;

    @NotNull @ManyToOne @JoinColumn(name = "loja_id", referencedColumnName = "id")
    private ETP etp;
}
