package com.goup.entities.estoque;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @NotNull @ManyToOne @JoinColumn(name = "etp_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ETP etp;
}
